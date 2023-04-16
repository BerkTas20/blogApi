package com.berktas.blogApi.service;


import com.berktas.blogApi.controller.requests.LoginRequest;
import com.berktas.blogApi.controller.requests.LoginResponse;

public interface AccountService {
    LoginResponse login(LoginRequest loginRequest);
}
