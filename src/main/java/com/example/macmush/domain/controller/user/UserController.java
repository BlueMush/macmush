package com.example.macmush.domain.controller.user;

import com.example.macmush.domain.dto.user.CreateUserRequest;
import com.example.macmush.domain.dto.user.FindUserResponse;
import com.example.macmush.domain.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
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

  @PostMapping("{username}")
  public void createUser(@PathVariable String username,
      @Valid @RequestBody CreateUserRequest request) {
    userService.createUser(username, request);
  }

  @GetMapping("{username}")
  public FindUserResponse findUser(@PathVariable String username) {
    return userService.findUser(username);
  }

  @GetMapping
  public Page<FindUserResponse> findAllUser(
      @PageableDefault(size = 10, sort = "createdAt") Pageable pageable) {
    return userService.findAllUser(pageable);
  }
}
