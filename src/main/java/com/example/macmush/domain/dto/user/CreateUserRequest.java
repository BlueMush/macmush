package com.example.macmush.domain.dto.user;

import com.example.macmush.domain.entity.Users;
import jakarta.validation.constraints.NotBlank;

public record CreateUserRequest(

    @NotBlank
    String password,

    @NotBlank
    String nickname,

    @NotBlank
    String email
) {

  public Users toUserEntity(String username) {
    return Users.builder()
        .username(username)
        .password(password)
        .nickname(nickname)
        .email(email)
        .build();
  }
}
