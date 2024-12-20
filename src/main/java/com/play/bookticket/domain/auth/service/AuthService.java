package com.play.bookticket.domain.auth.service;

import com.play.bookticket.common.exception.CustomException;
import com.play.bookticket.common.exception.ErrorCode;
import com.play.bookticket.common.util.util.JwtUtil;
import com.play.bookticket.domain.auth.dto.request.AuthLoginRequest;
import com.play.bookticket.domain.auth.dto.request.AuthSignupRequest;
import com.play.bookticket.domain.auth.dto.respone.AuthLoginResponse;
import com.play.bookticket.domain.auth.dto.respone.AuthSignupResponse;
import com.play.bookticket.domain.user.entity.User;
import com.play.bookticket.domain.user.enums.UserRole;
import com.play.bookticket.domain.user.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {

    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    /**
     * 회원가입기능
     * @param request(이메일, 비밀번호, 닉네임[선택])
     * @return
     */
    @Transactional
    public AuthSignupResponse signup(AuthSignupRequest request) {

        // 중복확인
        Optional<User> existingUser = userRepository.findByEmail(request.getEmail());
        if(existingUser.isPresent()){
            throw new CustomException(ErrorCode.AUTH_USER_EXISTING);
        }

        // 비밀번호 암호화
        String encodePassword = passwordEncoder.encode(request.getPassword());

        // 유저 객체 생성
        User user = new User(
                request.getEmail(),
                encodePassword,
                UserRole.of(request.getUserRole()),
                request.getNickname()
                );

        // DB 저장
        userRepository.save(user);

        // DTO 객체 반환
        return new AuthSignupResponse(
                user.getId(),
                user.getEmail(),
                user.getNickname()
        );
    }

    /**
     * 로그인
     * @param request(이메일, 비밀번호)
     * @return Token
     */
    public AuthLoginResponse login(@Valid AuthLoginRequest request) {

        // 가입 여부 확인
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND, User.class.getSimpleName()));

        // 비밀번호 확인
        checkPassword(request.getPassword(), user.getPassword());

        // 토큰생성
        String token = jwtUtil.createToken(
                user.getId(),
                user.getEmail(),
                user.getRole()
        );

        return new AuthLoginResponse(token);

    }

    /**
     * 토큰생성
     * @param requestPassword(유저 입력 비밀번호)
     * @param userPassword(유저 아이디 비밀번호)
     */
    private void checkPassword(String requestPassword, String userPassword){
        if(!passwordEncoder.matches(requestPassword, userPassword)){
            throw new CustomException(ErrorCode.AUTH_BAD_REQUEST_PASSWORD);
        }
    }
}
