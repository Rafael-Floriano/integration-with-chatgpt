package br.com.rafael.floriano.springintegrationwithchatgpt.service;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.awt.*;
import java.util.List;

@Service
public class ServiceGpt {
    public Mono<ChatGPTResponse> createResponde(String topic) {
        
    }
}

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
record ChatGPTResquest(String mode, String prompt, Double temperature
        , Integer maxToken, Double topP
        , Double frequencyPenalty, Double presencePenalty) {

}

record ChatGPTResponse(List<Choice> choices) {

}

record Choice(String text) {

}
