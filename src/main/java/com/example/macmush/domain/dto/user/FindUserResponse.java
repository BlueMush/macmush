package com.example.macmush.domain.dto.user;


import com.example.macmush.domain.entity.Users;

public record FindUserResponse(
    String username,

    String nickname,

    String email
) {

  public static FindUserResponse from(Users users) {
    return new FindUserResponse(users.getUsername(), users.getNickname(), users.getEmail());
  }
}
