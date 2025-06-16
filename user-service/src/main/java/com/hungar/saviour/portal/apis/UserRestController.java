package com.hungar.saviour.portal.apis;

import com.hungar.saviour.portal.dtos.LoginDTO;
import com.hungar.saviour.portal.dtos.ResponseDTO;
import com.hungar.saviour.portal.dtos.SigninDTO;
import com.hungar.saviour.portal.entity.UserEntity;
import com.hungar.saviour.portal.service.TokenService;
import com.hungar.saviour.portal.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class UserRestController {

    private final UserServiceImpl service;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenservice;

//    @PostMapping("/login")
//    public String login(Authentication authentication){
////        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
////                loginDTO.getUsername(),
////                loginDTO.getPassword(),  //@RequestBody LoginDTO loginDTO
////        ));
////        System.out.println(authentication.getPrincipal());
//        return tokenservice.generateToken(authentication);
//    }

    @PostMapping("/login")
    public ResponseDTO loginDetails(@RequestBody LoginDTO loginDTO){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDTO.getUsername(),
                loginDTO.getPassword()  //@RequestBody LoginDTO loginDTO
        ));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserEntity user = (UserEntity) authentication.getPrincipal();

        return ResponseDTO.builder()
                        .token(tokenservice.generateToken(authentication))
                .username(user.getUsername())
                .timestamp(LocalDateTime.now()).build();
    }

    // To Do this method is called by APIGateway to verify the token using WebClient
    public Boolean validateToken(@RequestParam("token") String token) throws Exception {
        return tokenservice.validateToken(token);
    }

    @GetMapping("/get")
    public String getMsg(){
        return "Hello from user";
    }

    @PostMapping("/signin")
    public String signUp(SigninDTO signinDTO){
        return service.userSignIn(signinDTO);
    }


}
