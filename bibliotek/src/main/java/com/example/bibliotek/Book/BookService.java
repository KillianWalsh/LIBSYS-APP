package com.example.bibliotek.Book;

import java.util.List;

public interface BookService {
    Book save(Book book);

    List<Book> findBookByTitle(String title);

    List<Book> findBookByAuthor(String author);

    List<Book> findBookByGenre(Genre genre);

    Book findBookById(Long id);

    List<Book> findStatus(BookStatus status);

    void reserve(Long borrowerId, Long bookId);

    List<Book> findReservations(Long reservation);

    void updateReservation(Long bookId);
}
