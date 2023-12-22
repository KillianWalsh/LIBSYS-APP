package com.example.bibliotek.WishList;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/wish", produces = "application/json; charset=UTF-8")
public class WishListController {
    WishListService wishListService;

    public WishListController(WishListService wishListService) {
        this.wishListService = wishListService;
    }

    @PostMapping("/save")
    public void save(String title, String author, String isbn) {
        wishListService.save(title, author, isbn);
    }

    @DeleteMapping("/deletebyid")
    public void deleteById(long wishId) {
        wishListService.deleteById(wishId);
    }

    @DeleteMapping("/deleteall")
    public void deleteAll() {
        wishListService.deleteAll();
    }

    @GetMapping("/getlastten")
    public List<WishList> findAll() {
        return wishListService.findAll();
    }

    @PostMapping("/saveWithProcedure")
    public void saveWithProcedure(@RequestParam String title, @RequestParam String author, @RequestParam String isbn) {
        wishListService.saveWithProcedure(title, author, isbn);
    }
}
