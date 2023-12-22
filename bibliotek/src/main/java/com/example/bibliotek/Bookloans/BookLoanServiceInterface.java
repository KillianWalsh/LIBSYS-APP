package com.example.bibliotek.Bookloans;

import java.util.List;

public interface BookLoanServiceInterface {
    //    void save(long borrowerId, long bookId);
    BookLoans save(Long borrowerId, Long bookId);

    List<BookLoans> findAllById(Long id);

    List<BookLoans> findAll();

    List<BookLoans> findLatest();

    List<BookLoans> loansToday();

    List<BookLoans> loansWeek();

    List<BookLoans> loansMonth(int year, int month);

    List<BookLoans> lateLoans();

    List<BookLoans> allLoansOfTitle(String title);
}
