package com.example.bibliotek;

import com.example.bibliotek.Admin.Admin;
import com.example.bibliotek.Admin.AdminService;
import com.example.bibliotek.Book.Book;
import com.example.bibliotek.Book.BookService;
import com.example.bibliotek.Book.BookStatus;
import com.example.bibliotek.Book.Genre;
import com.example.bibliotek.Borrower.Borrower;
import com.example.bibliotek.Borrower.BorrowerServiceInterface;
import com.example.bibliotek.Librarian.Librarian;
import com.example.bibliotek.Librarian.LibrarianService;
import com.example.bibliotek.LoginUser.Role;
import com.example.bibliotek.Rooms.RoomBookingService;
import com.example.bibliotek.Rooms.RoomBookingServiceInterface;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication

public class BibliotekApplication {

    public static void main(String[] args) {
        SpringApplication.run(BibliotekApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner run(BookService bookService, BorrowerServiceInterface borrowerServiceInterface,
                          LibrarianService librarianService, AdminService adminService, RoomBookingServiceInterface roomBookingServiceInterface) {
        return args -> {
            bookService.save(new Book(null, "test", "test", Genre.ANIMAL_AND_NATURE,
                    "test", "test", "test", BookStatus.WHACKED));

            bookService.save(new Book(null, "test2", "test2", Genre.HISTORY,
                    "test2", "test2", "test2", BookStatus.ONGOING_DELIVERY));

            bookService.save(new Book(null, "test3", "test3", Genre.LAW,
                    "test3", "test3", "test3", BookStatus.IN_STOCK));

            bookService.save(new Book(null, "test4", "test4", Genre.DATA_AND_IT,
                    "test4", "test4", "test4", BookStatus.NOT_IN_STOCK));

            bookService.save(new Book(null, "test5", "test5", Genre.PSYCHOLOGY,
                    "test5", "test5", "test5", BookStatus.IN_STOCK));

            bookService.save(new Book(null, "test6", "test6", Genre.FICTIONAL,
                    "test6", "test6", "test6", BookStatus.IN_STOCK));

            borrowerServiceInterface.save(new Borrower
                    (null, "borrower", "borrower", "borrower", "borrower", "password"));

            borrowerServiceInterface.save(new Borrower
                    (null, "borrower2", "borrower2", "borrower2", "borrower2", "password"));

            borrowerServiceInterface.save(new Borrower
                    (null, "borrower3", "borrower3", "borrower3", "borrower3", "password"));

            librarianService.saveLibrarian(new Librarian
                    ("librarian", "librarian", "librarian", "librarian", "password"));

            adminService.saveAdmin(new Admin
                    ("admin", "admin", "admin", "admin", "password"));


        };

    }
}

