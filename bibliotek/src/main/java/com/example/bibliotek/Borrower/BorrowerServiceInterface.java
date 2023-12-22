package com.example.bibliotek.Borrower;


import java.util.ArrayList;

public interface BorrowerServiceInterface {
    void save(Borrower borrower);

    //    Borrower save(Borrower borrower);
    Borrower register(Borrower borrower);

    Borrower findByEmail(String email);

    Borrower findById(Long id);

    ArrayList<Borrower> findAll();
}
