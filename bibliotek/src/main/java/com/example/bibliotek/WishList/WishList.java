package com.example.bibliotek.WishList;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@ToString
public class WishList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long wishId;
    private String title;
    private String author;
    private String isbn;
    private int numberOfWishes;

    public WishList() {
    }

    public WishList(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
    }
}
