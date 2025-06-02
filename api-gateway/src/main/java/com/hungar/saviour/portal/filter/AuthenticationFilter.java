package com.hungar.saviour.portal.filter;

import com.hungar.saviour.portal.handler.UnauthorizedException;
import com.hungar.saviour.portal.proxy.AuthenticationProxy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Component
@Slf4j
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.NameConfig> {

    private final RouteValidator validator;
    private final AuthenticationProxy proxy;

    public AuthenticationFilter(RouteValidator validator,AuthenticationProxy proxy){
        super(NameConfig.class);
        this.validator = validator;
        this.proxy = proxy;
    }

    @Override
    public GatewayFilter apply(AuthenticationFilter.NameConfig config) {
        return (((exchange, chain) -> {
            log.info("Received Request : "+ exchange.getRequest().toString());

            if(validator.publicEndPoints.test(exchange.getRequest())){
                if(!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)){
                    throw new UnauthorizedException("Missing Auth Header");
                }

                String authHeader = Objects.requireNonNull((exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION)).get(0));

                if(authHeader != null && authHeader.startsWith("Bearer ")){
                    authHeader = authHeader.substring(7);
                }
                log.info("Received token: "+ authHeader);
                return validateToken(authHeader)
                        .flatMap(valid -> {
                            if(Boolean.TRUE.equals(valid)){
                                log.info("Valid token received");
                                return chain.filter(exchange);
                            }else {
                                log.info("Token validation failed");
                                ServerHttpResponse response = exchange.getResponse();
                                return response.setComplete();
                            }
                        });
            }
            return chain.filter(exchange);
        }));
    }

    public static class Config{

    }

    private Mono<Boolean> validateToken(String token){
        log.info("Validating token: "+token);
        return  proxy.validateToken(token);
    }


}
