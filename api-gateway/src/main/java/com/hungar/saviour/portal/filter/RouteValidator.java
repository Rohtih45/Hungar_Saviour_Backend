package com.hungar.saviour.portal.filter;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;
@Component
public class RouteValidator {

    public static final List<String> openApiEndPoints = List.of(
            "/users/login",
            "/users/signup",
            "/users/validate"
    );

    public final Predicate<ServerHttpRequest>  publicEndPoints =
            request -> openApiEndPoints.stream()
                    .noneMatch(uri -> request.getURI().getPath().contains(uri));
}
