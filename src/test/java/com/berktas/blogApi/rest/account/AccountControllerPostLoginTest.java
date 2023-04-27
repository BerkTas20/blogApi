package com.berktas.blogApi.rest.account;


import com.berktas.blogApi.controller.requests.LoginRequest;
import com.berktas.blogApi.controller.requests.SaveAndUpdateUserRequest;
import com.berktas.blogApi.model.dto.UserDto;
import com.berktas.blogApi.model.enums.Role;
import com.berktas.blogApi.repository.UserRepository;
import com.berktas.blogApi.rest.PlatformTestWithAuthentification;
import com.berktas.blogApi.rest.RequestSpec;
import com.berktas.blogApi.rest.ResponseSpec;
import com.berktas.blogApi.utils.TestUserUtility;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;

public class AccountControllerPostLoginTest extends PlatformTestWithAuthentification {

    @Autowired
    TestUserUtility testUserUtility;


    @BeforeAll
    public void before() {
        testUserUtility.getOrCreateTestAdmin();
    }


    @org.junit.jupiter.api.Test
    public void testAdminLogin() {
        LoginRequest loginRequest = LoginRequest.builder()
                .username("admin")
                .password("12345").build();

        RequestSpec.given()
                .jsonRequest().body(loginRequest)
                .when().post(path())
                .then()
                .spec(ResponseSpec.isOkResponse());
    }


    @org.junit.jupiter.api.Test
    public void testNoSuchUserLogin() {
        LoginRequest loginRequest = LoginRequest.builder()
                .username("noSuchUser")
                .password("12345").build();


        RequestSpec.given()
                .jsonRequest().body(loginRequest)
                .when().post(path())
                .then()
                .spec(ResponseSpec.isBadRequestResponse());
    }

    private String path() {
        return "/user";
    }


}
