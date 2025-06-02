package com.hungar.saviour.portal.proxy;

import com.hungar.saviour.portal.dtos.AuthRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import reactor.core.scheduler.Schedulers;

@Component
@RequiredArgsConstructor
public class AuthenticationProxy {

    private WebClient.Builder webClientBuilder;

    public Mono<String> getToken(AuthRequest request){
        return webClientBuilder
                .build()
                .post()
                .uri("http://user-service/users/login")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(String.class)
                .subscribeOn(Schedulers.boundedElastic());
    }

    public Mono<Boolean> validateToken(String token){
        return webClientBuilder.build().get()
                .uri("http://user-service/users/validate?token="+token)
                .retrieve()
                .bodyToMono(Boolean.class)
                .subscribeOn(Schedulers.boundedElastic());
    }
}
