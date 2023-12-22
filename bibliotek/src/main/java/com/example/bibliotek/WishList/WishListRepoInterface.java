package com.example.bibliotek.WishList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishListRepoInterface extends JpaRepository<WishList, Long> {
}
