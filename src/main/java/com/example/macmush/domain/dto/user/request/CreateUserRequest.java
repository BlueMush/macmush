package com.example.macmush.domain.dto.user.request;

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

  public Users toUserEntity(String userId) {
    return Users.builder()
        .username(userId)
        .password(password)
        .nickname(nickname)
        .email(email)
        .build();
  }
}
