package com.hungar.saviour.portal.service.impl;

import com.hungar.saviour.portal.dtos.SigninDTO;
import com.hungar.saviour.portal.entity.RoleEntity;
import com.hungar.saviour.portal.entity.UserEntity;
import com.hungar.saviour.portal.repository.UserRepo;
import com.hungar.saviour.portal.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public String userSignIn(SigninDTO signinDTO) {
        UserEntity entity = UserEntity.builder().username(signinDTO.getUsername())
                .password(passwordEncoder.encode(signinDTO.getPassword()))
                .mobileNumber(signinDTO.getMobileNumber())
                .roles(List.of(new RoleEntity("Admin")))
                .build();
        userRepo.save(entity);
        return "User Created";
    }
}
