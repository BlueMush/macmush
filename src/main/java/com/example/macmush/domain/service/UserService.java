package com.example.macmush.domain.service;

import com.example.macmush.domain.dto.user.CreateUserRequest;
import com.example.macmush.domain.dto.user.FindUserResponse;
import com.example.macmush.domain.entity.Users;
import com.example.macmush.domain.repogitory.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

  private final UserRepository userRepository;

  @Transactional
  public void createUser(String username, CreateUserRequest request) {
    var user = request.toUserEntity(username);
    userRepository.save(user);
  }

  public FindUserResponse findUser(String username) {
    var user = userRepository.findByUsername(username).orElseThrow();
    return FindUserResponse.from(user);
  }

  public Page<FindUserResponse> findAllUser(
      Pageable pageable
  ) {
    Page<Users> userPage = userRepository.findAll(pageable);
    return userPage.map(FindUserResponse::from);
  }
}
