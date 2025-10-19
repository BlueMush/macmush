package com.example.macmush.domain.controller.user;

import com.example.macmush.domain.dto.user.request.CreateUserRequest;
import com.example.macmush.domain.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

  private final UserService userService;

  @PostMapping("{userId}")
  public void createUser(@PathVariable String userId,
      @Valid @RequestBody CreateUserRequest request) {
    userService.createUser(userId, request);
  }
}
