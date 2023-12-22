package com.example.bibliotek.WishList;

import java.util.List;

public interface WishListServiceInterface {
    void save(String title, String author, String isbn);

    void deleteById(long wishId);

    void deleteAll();

    List<WishList> findAll();

    void saveWithProcedure(String title, String author, String isbn);
}
