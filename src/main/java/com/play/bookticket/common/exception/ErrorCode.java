package com.play.bookticket.common.exception;

import com.play.bookticket.domain.user.enums.UserRole;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Arrays;

@Getter
public enum ErrorCode {

    // ex)
    /*AUTH_USER_EXISTING(HttpStatus.CONFLICT, "해당 이메일으로 가입된 유저가 이미 존재합니다."),
    AUTH_USER_NOT_FOUND(HttpStatus.NOT_FOUND, "가입되지 않은 유저입니다."),
    AUTH_BAD_REQUEST_PASSWORD(HttpStatus.BAD_REQUEST, "입력하신 비밀번호가 올바르지 않습니다. 비밀번호를 다시 확인하고 입력해 주세요."),
    AUTH_USER_DELETED(HttpStatus.NOT_FOUND, "탈퇴한 유저 입니다."),*/


    // Auth
    AUTH_USER_EXISTING(HttpStatus.ALREADY_REPORTED, "이미 가입된 이메일입니다."),
    AUTH_BAD_REQUEST_PASSWORD(HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다"),

    NO_AUTHORITY(HttpStatus.FORBIDDEN, "%s에 대한권한이 없습니다."),
    NOT_FOUND(HttpStatus.NOT_FOUND, "%s을(를) 찾지못했습니다.");

    private final HttpStatus status;
    private final String message;

    ErrorCode(HttpStatus httpStatus, String message){
        this.status = httpStatus;
        this.message = message;
    }

    public String customMessage(String detail) {
        return String.format(message, detail);
    }
}
