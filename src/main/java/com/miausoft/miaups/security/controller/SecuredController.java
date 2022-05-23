package com.miausoft.miaups.security.controller;

import com.miausoft.miaups.Role;
import com.miausoft.miaups.interceptors.Authorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecuredController {
    @GetMapping("/secured")
    @ResponseBody
    @Authorize(roles = { Role.ADMIN })
    public String file() {
        return "Response from secure endpoint.";
    }
}