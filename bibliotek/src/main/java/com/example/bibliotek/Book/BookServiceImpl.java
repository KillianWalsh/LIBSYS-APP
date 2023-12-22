package com.example.bibliotek.Book;

import com.example.bibliotek.Bookloans.BookLoanRepoInterface;
import com.example.bibliotek.Bookloans.BookLoanService;
import com.example.bibliotek.Bookloans.BookLoans;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookLoanRepoInterface bookLoanRepoInterface;
    private final BookLoanService bookLoanService;


    @Override
    public Book save(Book book) {
        book.setReservation(0L);
        return bookRepository.save(book);
    }

    @Override
    public List<Book> findBookByTitle(String title) {
        return bookRepository.findBookByTitle(title);
    }

    @Override
    public List<Book> findBookByAuthor(String author) {
        List<Book> list = bookRepository.findBookByAuthor(author);
        HashSet<String> hashSet = new HashSet<>();
        list.removeIf(e -> !hashSet.add(e.getTitle()));
        return list;
    }

    @Override
    public List<Book> findBookByGenre(Genre genre) {
        List<Book> list = bookRepository.findBookByGenre(genre);
        HashSet<String> hashSet = new HashSet<>();
        list.removeIf(e -> !hashSet.add(e.getTitle()));
        return list;
    }

    @Override
    public Book findBookById(Long id) {
        return bookRepository.findBookById(id);
    }

    //Hämtar alla böcker med viss status. La även kod här för att uppdatera sena lån, finns även i bookloanservice
    @Override
    public List<Book> findStatus(BookStatus status) {
        List<Book> books = bookRepository.findAll();
        //uppdaterar sena lån först
        List<BookLoans> bookLoans = bookLoanRepoInterface.findAll();
        LocalDate today = LocalDate.now();
        for (BookLoans bo : bookLoans) {
            if (bo.getDateOfBookReturn().isBefore(today)) {
                Book book = bookRepository.findBookById(bo.getBookId());
                if (!book.getStatus().equals(BookStatus.LATE)) {
                    book.setStatus(BookStatus.LATE);
                    bookRepository.save(book);
                }
            }
        }
        //nu hämtar alla med viss status
        List<Book> temp = new ArrayList<>();
        for (Book b : books) {
            if (b.getStatus().equals(status)) {
                temp.add(b);
            }
        }
        return temp;
    }

    //Sätter reservation för en viss bok, tar emot id för låntagare och bok. Kontrollerar att den ej redan är reserverad
    //Kontrollera även att låntagare som redan lånar boken inte får reservera den
    @Override
    public void reserve(Long borrowerId, Long bookId) {
        boolean isAllowed = true;
        //Kontrollerar att bok har status ON_LOAN
        Book book = bookRepository.findBookById(bookId);
        if (book.getStatus().equals(BookStatus.ON_LOAN)) {
            List<BookLoans> bookLoans = bookLoanService.findAllById(borrowerId);
            for (BookLoans b : bookLoans) {
                if (b.getBookId() == bookId) {
                    //Du lånar den här boken och får inte reservera den
                    isAllowed = false;
                    break;
                }
            }
            //Kontrollerar att bok inte redan är reserverad
            if (isAllowed) {
                if (book.getReservation() == 0) {
                    book.setReservation(borrowerId);
                    bookRepository.save(book);
                }
            }
        }
    }

    @Override
    public List<Book> findReservations(Long reservation) {
        return bookRepository.findBookByReservation(reservation);
    }

    @Override
    public void updateReservation(Long bookId) {
        Book book = bookRepository.findBookById(bookId);
        book.setReservation(0L);
        bookRepository.save(book);
    }
}
