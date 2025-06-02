package com.hungar.saviour.portal.service;

import com.hungar.saviour.portal.dtos.SigninDTO;

public interface UserService {

    String userSignIn(SigninDTO signinDTO);
}
