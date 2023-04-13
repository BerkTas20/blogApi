package com.berktas.blogApi.model.dto;

public record UserDto(Long id,
                      String firstName,
                      String lastName,
                      String username,
                      String phone,
                      String email,
                      String password,
                      String role) {


}
