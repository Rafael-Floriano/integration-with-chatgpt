package br.com.rafael.floriano.springintegrationwithchatgpt.service;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.awt.*;
import java.util.List;

@Service
public class ServiceGpt {

    private WebClient webClient;
    //Key sempre no application.proprieties
    @Value("${spring.openai.api.key}")
    String apiKey = "null";

    public ServiceGpt(WebClient.Builder builder) {
        this.webClient = builder.baseUrl("https://api.openai.com/v1/chat/completions")
                .defaultHeader("Content-Type","application/json")
                .defaultHeader("Authorization", String.format("Bearer %s", apiKey))
                .build();
    }

    public Mono<String> createGPTResponse(String topic) {
        ChatGPTResquest resquest = createQuestionResquest(topic);

        return webClient.post().bodyValue(resquest)
                .retrieve()
                .bodyToMono(ChatGPTResponse.class).map(chatGPTResponse -> chatGPTResponse.choices().get(0).text());
    }

    public ChatGPTResquest createQuestionResquest(String topic) {
        String question = "O que devo estudar sobre: "+ topic;
        return new ChatGPTResquest("gpt-3.5-turbo", question, 0.3, 2000, 1.0, 0.0, 0.0);
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
