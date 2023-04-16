package com.berktas.blogApi.controller;

import com.berktas.blogApi.controller.requests.LoginRequest;
import com.berktas.blogApi.controller.requests.LoginResponse;
import com.berktas.blogApi.controller.requests.MeResponse;
import com.berktas.blogApi.core.security.SpringContext;
import com.berktas.blogApi.core.utils.annotation.IsAuthenticated;
import com.berktas.blogApi.core.utils.annotation.PermitAllCustom;
import com.berktas.blogApi.service.AccountService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
@IsAuthenticated
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @GetMapping
    public MeResponse meResponse() {
        return SpringContext.getMe();
    }

    @PostMapping("/login")
    @PermitAllCustom
    public LoginResponse login(@Valid @RequestBody LoginRequest loginRequest) {

        return accountService.login(loginRequest);
    }
}