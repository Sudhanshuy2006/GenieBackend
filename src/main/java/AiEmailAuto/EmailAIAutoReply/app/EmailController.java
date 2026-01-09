package AiEmailAuto.EmailAIAutoReply.app;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/email")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class EmailController {

    private final EmailGenerateService emailGenerateService;

    @PostMapping("/generate")
    public ResponseEntity<String> generateEmail(@RequestBody RequestEmail requestEmail){
        String response = emailGenerateService.generateService(requestEmail);
        return ResponseEntity.ok(response);
    }
}