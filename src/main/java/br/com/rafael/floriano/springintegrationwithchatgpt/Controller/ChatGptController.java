package br.com.rafael.floriano.springintegrationwithchatgpt.Controller;

import br.com.rafael.floriano.springintegrationwithchatgpt.service.ServiceGpt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/sendMessage")
public class ChatGptController {
    @Autowired
    ServiceGpt serviceGpt;
    @PostMapping("/sendPromptMessage")
    public Mono<String> sendPromptMessage(@RequestParam String prompt) {
        return serviceGpt.createGPTResponse(prompt);
    }
}
