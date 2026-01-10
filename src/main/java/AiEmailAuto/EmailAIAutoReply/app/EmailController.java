package AiEmailAuto.EmailAIAutoReply.app;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/email")
@AllArgsConstructor
public class EmailController {

    private final EmailGenerateService emailGenerateService;

    @PostMapping("/generate")
    public ResponseEntity<String> generateEmail(@RequestBody RequestEmail requestEmail) {
        return ResponseEntity.ok(
                emailGenerateService.generateService(requestEmail)
        );
    }
}

