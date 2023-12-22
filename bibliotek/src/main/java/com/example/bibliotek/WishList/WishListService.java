package com.example.bibliotek.WishList;

import org.springframework.stereotype.Service;

@Service
public class WishListService implements WishListServiceInterface {
    WishListRepoInterface wishListRepoInterface;

    public WishListService(WishListRepoInterface wishListRepoInterface) {
        this.wishListRepoInterface = wishListRepoInterface;
    }

    public void save(String title, String author, String isbn) {
        WishList wishList = new WishList(title, author, isbn);
        wishListRepoInterface.save(wishList);
    }
}
