package com.play.bookticket.domain.auth.dto.respone;

import lombok.Getter;

@Getter
public class AuthSignupResponse {

    private Long id;
    private String email;
    private String nickname;

    public AuthSignupResponse(Long id, String email, String nickname) {
        this.id = id;
        this.email = email;
        this.nickname = (nickname != null) ? nickname : "";
    }

}
