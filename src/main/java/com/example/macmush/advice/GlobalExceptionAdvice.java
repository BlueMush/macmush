package com.example.macmush.advice;

import com.example.macmush.domain.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionAdvice {

  /**
   * 500 - Internal Server Error (서버 내부 오류) 위에서 처리하지 못한 모든 예외를 처리
   */
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ResponseDto> handleException(Exception e) {
    ResponseDto response = new ResponseDto(e.getMessage());
    return ResponseEntity
        .status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(response);
  }
}
