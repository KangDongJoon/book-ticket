package com.play.bookticket.domain.user.repository;

import com.play.bookticket.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
