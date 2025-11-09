package com.example.macmush.domain.dto;

/**
 * 표준 에러 응답 DTO
 *
 * @param message 에러 메시지
 */
public record ResponseDto(
    String message
) {

}
