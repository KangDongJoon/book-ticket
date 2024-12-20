package com.play.bookticket.domain.auth.contorller;

import com.play.bookticket.domain.auth.dto.request.AuthLoginRequest;
import com.play.bookticket.domain.auth.dto.request.AuthSignupRequest;
import com.play.bookticket.domain.auth.dto.respone.AuthLoginResponse;
import com.play.bookticket.domain.auth.dto.respone.AuthSignupResponse;
import com.play.bookticket.domain.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    // 회원가입
    @PostMapping("/v1/auth/signup")
    public ResponseEntity<AuthSignupResponse> signup(@Valid @RequestBody AuthSignupRequest request){
        return ResponseEntity.ok(authService.signup(request));
    }

    // 로그인
    @PostMapping("/v1/auth/login")
    public ResponseEntity<AuthLoginResponse> login(@Valid @RequestBody AuthLoginRequest request){
        return ResponseEntity.ok(authService.login(request));
    }
}
