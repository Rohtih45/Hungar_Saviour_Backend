package com.hungar.saviour.portal.config;

import com.hungar.saviour.portal.dtos.RsaKeyConfigurationProperties;
import com.hungar.saviour.portal.service.CustomUserrDetailsService;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class HungarSaviourSecurity {
    @Autowired
    private RsaKeyConfigurationProperties rsaKeyConfigurationProperties;
    @Autowired
    private CustomUserrDetailsService customUserrDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(){
        var authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(customUserrDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());

        return new ProviderManager(authenticationProvider);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/users/login", "/users/signin", "/users/validate",
                            "/swagger-ui.html", "/v3/api-docs/**", "/v3/api-docs.yaml",
                            "/swagger-ui/**","/swagger-ui/index.html").permitAll();
                    auth.anyRequest().authenticated();
                })
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .oauth2ResourceServer((oauth2)->oauth2.jwt(jwt -> jwt.decoder(jwtDecoder())))
                .httpBasic(Customizer.withDefaults())
                .build();
    }
//
    @Bean
    public JwtEncoder jwtEncoder(){
        JWK jwk = new RSAKey.Builder((rsaKeyConfigurationProperties.publicKey()))
                .privateKey(rsaKeyConfigurationProperties.privateKey()).build();
        JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
        return new NimbusJwtEncoder(jwks);
    }

    @Bean
    public JwtDecoder jwtDecoder(){
        return NimbusJwtDecoder.withPublicKey(rsaKeyConfigurationProperties.publicKey()).build();
    }
}
