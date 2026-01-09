package AiEmailAuto.EmailAIAutoReply.app;

import lombok.Data;

@Data
public class RequestEmail {
    private String emailContent;
    private String tone;
}