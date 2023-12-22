package com.example.bibliotek.Bookloans;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

import java.util.List;

public interface BookLoanRepoInterface extends JpaRepository<BookLoans, Long> {

    @Procedure
    void updateBookLoanStatus(Long borrowerId, Long bookId);
}
