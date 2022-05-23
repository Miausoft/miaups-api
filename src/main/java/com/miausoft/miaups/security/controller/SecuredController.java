package com.miausoft.miaups.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
public class SecuredController {
    @GetMapping("/secured")
    @ResponseBody
    @PreAuthorize("hasAuthority('APPROLE_d5728e1e-7e18-4f99-8a4d-22933d9a4dca')")
    public String file() {
        return "Response from secure endpoint.";
    }
}