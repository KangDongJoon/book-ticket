package com.play.bookticket.domain.auth.dto.respone;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthLoginResponse {

    private final String bearerToken;
}
