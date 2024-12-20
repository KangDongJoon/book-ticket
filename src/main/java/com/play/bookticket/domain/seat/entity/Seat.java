package com.play.bookticket.domain.seat.entity;

import com.play.bookticket.domain.user.enums.UserRole;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

@Getter
public class Seat {

    private final Long userId;
    private final String name;
    private final String email;
    private final Collection<? extends GrantedAuthority> authorities;

    public Seat(Long userId, String name, String email, UserRole role) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.authorities = List.of(new SimpleGrantedAuthority(role.getUserRole()));
    }
}
