package AiEmailAuto.EmailAIAutoReply.app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

@Service
public class EmailGenerateService {

    private final WebClient webClient;

    @Value("${gemini.api.url}")
    private String geminiApiUrl;

    @Value("${gemini.api.key}")
    private String geminiApiKey;

    public EmailGenerateService() {
        this.webClient = WebClient.create();
    }

    public String generateService(RequestEmail requestEmail) {
        try {
            // Build the prompt
            String build = buildprompt(requestEmail);

            // Make request body
            Map<String, Object> generateEmail = Map.of(
                    "contents", new Object[]{
                            Map.of("parts", new Object[]{
                                    Map.of("text", build)
                            })
                    }
            );

            // DO request and get Response
            String response = webClient.post()
                    .uri(geminiApiUrl)
                    .header("Content-Type", "application/json")
                    .header("x-goog-api-key", geminiApiKey)  // NEW: API key in header
                    .bodyValue(generateEmail)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            // Extract and return
            return extractResponseReturn(response);

        } catch (Exception e) {
            return "Error calling Gemini API: " + e.getMessage();
        }
    }

    private String extractResponseReturn(String response) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(response);
            return rootNode.path("candidates")
                    .get(0)
                    .path("content")
                    .path("parts")
                    .get(0)
                    .path("text")
                    .asText();

        } catch (Exception e) {
            return "Error processing response: " + e.getMessage();
        }
    }

    private String buildprompt(RequestEmail requestEmail) {
        StringBuilder prompt = new StringBuilder();
        prompt.append("Generate a professional email ");

        if (requestEmail.getTone() != null && !requestEmail.getTone().isEmpty()) {
            prompt.append("with a ")
                    .append(requestEmail.getTone())
                    .append(" tone. ");
        }

        prompt.append("Based on the original email:\n")
                .append(requestEmail.getEmailContent());

        return prompt.toString();
    }
}