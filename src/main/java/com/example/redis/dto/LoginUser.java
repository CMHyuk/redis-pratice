package com.example.redis.dto;

import lombok.Getter;

@Getter
public class LoginUser {

    private Long id;

    public LoginUser(Long id) {
        this.id = id;
    }

}
