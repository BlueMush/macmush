package com.example.macmush.domain.service;

import com.example.macmush.domain.dto.user.request.CreateUserRequest;
import com.example.macmush.domain.repogitory.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

  private final UserRepository userRepository;

  @Transactional
  public void createUser(String userId, CreateUserRequest request) {
    var user = request.toUserEntity(userId);
    userRepository.save(user);
  }
}
