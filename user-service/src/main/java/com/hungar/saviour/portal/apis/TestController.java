package com.hungar.saviour.portal.apis;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/secured")
    @PreAuthorize("hasAuthority('SCOPE_Admin')")
    public String getSecuredMsg(){
        return "Authenticated Secured Endpoint";
    }
}
