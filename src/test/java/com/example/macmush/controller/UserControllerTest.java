package com.example.macmush.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.macmush.domain.controller.user.UserController;
import com.example.macmush.domain.dto.user.FindUserResponse;
import com.example.macmush.domain.service.UserService;
import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.api.introspector.ConstructorPropertiesArbitraryIntrospector;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(UserController.class)
class UserControllerTest {

  private final FixtureMonkey fixtureMonkey = FixtureMonkey.builder()
      .objectIntrospector(ConstructorPropertiesArbitraryIntrospector.INSTANCE)
      .build();

  @Autowired
  private MockMvc mockMvc;

  @MockitoBean
  private UserService userService;

  @Test
  @DisplayName("유저 조회 성공")
  void userControllerSuccess() throws Exception {
    var requestName = "df";

    FindUserResponse response = fixtureMonkey.giveMeOne(FindUserResponse.class);

    when(userService.findUser(requestName)).thenReturn(response);

    mockMvc.perform(get("/api/users/{userName}", requestName))
        .andExpect(status().isOk());
  }

  @Test
  @DisplayName("유저 조회 실패")
  void userControllerNoSuchCase() throws Exception {
//    when(userService.findUser(ArgumentMatchers.anyString()))
//        .thenThrow(new RuntimeException());
    when(userService).thenThrow(Exception.class);
    mockMvc.perform(get("/api/users/{userName}", "df"))
        .andExpect(status().isInternalServerError());
  }
}