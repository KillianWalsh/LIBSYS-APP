package com.example.bibliotek.Book;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "book")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "book_id", nullable = false)
    private Long id;

    @NotEmpty(message = "Title may not be empty")
    @Column(name = "book_title")
    private String title;

    @NotEmpty(message = "Author may not be empty")
    @Column(name = "author")
    private String author;

    @Enumerated(EnumType.STRING)
    @Column(name = "genre")
    private Genre genre;

    @NotEmpty(message = "Publisher may not be empty")
    @Column(name = "publisher")
    private String publisher;

    @NotEmpty(message = "Publication Date may not be empty")
    @Column(name = "publication_date")
    private String publicationDate;

    @NotEmpty(message = "ISBN may not be empty")
    @Column(name = "ISBN")
    private String isbn;

    @Column(name = "book_status")
    @Enumerated(EnumType.STRING)
    private BookStatus status;

    @Column(name = "reservation")
    @Value("${reservation:0}")
    private Long reservation;

    public Book(Long id, String title, String author, Genre genre, String publisher, String publicationDate, String isbn, BookStatus status) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.publisher = publisher;
        this.publicationDate = publicationDate;
        this.isbn = isbn;
        this.status = status;
    }
}
