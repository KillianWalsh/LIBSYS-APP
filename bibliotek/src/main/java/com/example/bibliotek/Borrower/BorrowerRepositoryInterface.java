package com.example.bibliotek.Borrower;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BorrowerRepositoryInterface extends JpaRepository<Borrower, Long> {
    Optional<Borrower> findByEmail(String email);
}
