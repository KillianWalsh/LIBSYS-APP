package com.example.bibliotek.UserApp;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAppRepository<T extends UserApp> extends JpaRepository<T, Long> {
}
