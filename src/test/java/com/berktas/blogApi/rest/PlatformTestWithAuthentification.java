package com.berktas.blogApi.rest;



import com.berktas.blogApi.model.User;
import com.berktas.blogApi.utils.TestUserUtility;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

public class PlatformTestWithAuthentification extends PlatformTest {

    @Autowired
    private TestUserUtility testUserUtility;

    private User admin;

    @PostConstruct
    public void before_PlatformTestWithAuthentification() {
        admin = testUserUtility.getOrCreateTestAdmin();

    }

    public User getAdmin() {
        return admin;
    }



}
