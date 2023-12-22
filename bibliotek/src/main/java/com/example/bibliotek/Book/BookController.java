package com.example.bibliotek.Book;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RequiredArgsConstructor
@CrossOrigin
@RestController
@RequestMapping(path = "/book", produces = "application/json; charset=UTF-8")
public class BookController {

    private final BookService bookService;


    @PostMapping("/save")
    ResponseEntity<Book> saveBook(@RequestBody Book book) {
//        if (!ISBN.isISBNValid(book.getIsbn())){
//            System.out.println("Felaktigt ISBN!");
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }

        return new ResponseEntity<>(bookService.save(book), HttpStatus.CREATED);
    }


    @GetMapping("/findbytitle")
    public List<Book> findBookByTitle(@RequestParam String title) {
        System.out.println(bookService.findBookByTitle(title).toString());
        return bookService.findBookByTitle(title);
    }

    @GetMapping("/findbyauthor")
    public List<Book> findBookByAuthor(@RequestParam String author) {
        return bookService.findBookByAuthor(author);
    }

    @GetMapping("/findbygenre")
    public List<Book> findBookByGenre(@RequestParam Genre genre) {
        return bookService.findBookByGenre(genre);
    }

    @GetMapping("/findbookbyid")
    @ResponseBody
    public ResponseEntity<Book> findBookById(@RequestParam Long id) {
        return new ResponseEntity<>(bookService.findBookById(id), HttpStatus.OK);
    }

    //Hämtar alla böcker med viss status
    @GetMapping("/findstatus")
    @ResponseBody
    public List<Book> findStatus(@RequestParam String status) {
        BookStatus currentStatus = BookStatus.valueOf(status);
        return bookService.findStatus(currentStatus);
    }

    //Sätter reservation för en viss bok, tar emot id för låntagare och bok
    @PostMapping("/reservation")
    public void reserveBook(long borrowerId, long bookId) {
        bookService.reserve(borrowerId, bookId);
    }

    //Hämtar alla dina reservationer(reserverade böcker)
    @GetMapping("/findreservations")
    public List<Book> findReservations(long reservation) {
        return bookService.findReservations(reservation);
    }

    //Sätter reservation till default = 0
    @PostMapping("/updatereservation")
    public void updateReservation(long bookId) {
        bookService.updateReservation(bookId);
    }
}

