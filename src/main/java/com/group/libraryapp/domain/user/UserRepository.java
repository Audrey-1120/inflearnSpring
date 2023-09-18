package com.group.libraryapp.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> { //JpaRepository를 상속하기 때문에 어노테이션 없어도 스프링 빈으로 등록됨.

    Optional<User> findByName(String name);

    boolean existsByName(String name);
}
