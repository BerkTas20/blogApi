package com.berktas.blogApi.service.impl;


import com.berktas.blogApi.controller.requests.LoginRequest;
import com.berktas.blogApi.controller.requests.LoginResponse;
import com.berktas.blogApi.core.exception.IncorrectEntryException;
import com.berktas.blogApi.core.exception.validator.UserDisabledException;
import com.berktas.blogApi.core.security.CustomUserDetails;
import com.berktas.blogApi.core.security.JwtTokenUtil;
import com.berktas.blogApi.core.security.SpringContext;
import com.berktas.blogApi.model.User;
import com.berktas.blogApi.repository.UserRepository;

import com.berktas.blogApi.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final JwtTokenUtil jwtTokenUtil;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;


    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
            CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
            User currentUser = customUserDetails.getUser();
            final String token = jwtTokenUtil.generate(customUserDetails, loginRequest.isRememberMe());
            SecurityContext sc = SecurityContextHolder.getContext();
            sc.setAuthentication(authentication);
            HttpServletRequest request = SpringContext.getCurrentHttpRequest().get();
            HttpSession session = request.getSession(true);
            session.setAttribute(SPRING_SECURITY_CONTEXT_KEY, sc);
            return new LoginResponse(currentUser.getUsername(), token);

        } catch (BadCredentialsException badCredentialsException) {

            throw new IncorrectEntryException("");

        } catch (DisabledException disabledException) {
            throw new UserDisabledException("");
        }
    }
}

