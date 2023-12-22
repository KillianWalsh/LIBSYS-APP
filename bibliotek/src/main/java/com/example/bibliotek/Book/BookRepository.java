package com.example.bibliotek.Book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findBookByTitle(String title);

    List<Book> findBookByAuthor(String author);

    List<Book> findBookByGenre(Genre genre);

    Book findBookById(Long id);

    List<Book> findBookByReservation(long reservation);
}
