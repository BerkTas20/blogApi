package com.berktas.blogApi.controller;

import com.berktas.blogApi.controller.requests.LoginRequest;
import com.berktas.blogApi.controller.requests.LoginResponse;
import com.berktas.blogApi.controller.requests.MeResponse;
import com.berktas.blogApi.core.security.SpringContext;
import com.berktas.blogApi.core.utils.annotation.IsAuthenticated;
import com.berktas.blogApi.core.utils.annotation.PermitAllCustom;
import com.berktas.blogApi.service.AccountService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/account")
@IsAuthenticated
@RequiredArgsConstructor
@Tag(name = "Account")
public class AccountController {
    private final AccountService accountService;

    @GetMapping
    public MeResponse me() {
        return SpringContext.getMe();
    }


    @PostMapping("/login")
    @PermitAllCustom
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(accountService.login(loginRequest));
    }

}
