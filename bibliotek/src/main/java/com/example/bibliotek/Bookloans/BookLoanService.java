package com.example.bibliotek.Bookloans;

import com.example.bibliotek.Book.Book;
import com.example.bibliotek.Book.BookRepository;
import com.example.bibliotek.Book.BookStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookLoanService implements BookLoanServiceInterface {

    private final BookLoanRepoInterface bookLoanRepoInterface;
    private final BookRepository bookRepository;

    //Flyttade logiken hit från BookLoans get och set 20220526
    //Sparar ett lån
    @Override
    public BookLoans save(Long borrowerId, Long bookId) {

        Book book = bookRepository.findBookById(bookId);

        if (book.getStatus().equals(BookStatus.IN_STOCK)) {
            if (Objects.equals(book.getReservation(), borrowerId)) {
                book.setReservation(0L);
                bookRepository.save(book);
            }
            if (book.getReservation() == 0) {
                BookLoans bookLoans = new BookLoans(borrowerId, bookId);
                LocalDate today = LocalDate.now();
                LocalDate loanPeriod = today.plusDays(14);
                bookLoans.setDateOfBookLoanDay(today);
                bookLoans.setDateOfBookReturn(loanPeriod);
                bookLoans.setTitle(book.getTitle());
                book.setStatus(BookStatus.ON_LOAN);
                bookRepository.save(book);
                return bookLoanRepoInterface.save(bookLoans);
            }
        }
        return null;
    }

    //Hämtar alla lån för en viss låntagare
    @Override
    public List<BookLoans> findAllById(Long id) {
        List<BookLoans> temp = new ArrayList<>();
        List<BookLoans> bookLoans = bookLoanRepoInterface.findAll();
        for (BookLoans b : bookLoans) {
            if (b.getBorrowerId() == id) {
                temp.add(b);
            }
        }
        return temp;
    }

    //Hämtar ALLA lån
    @Override
    public List<BookLoans> findAll() {
        return bookLoanRepoInterface.findAll();
    }

    //Hämtar 100 senaste lånen med absolut senaste först
    @Override
    public List<BookLoans> findLatest() {
        List<BookLoans> bookLoans = bookLoanRepoInterface.findAll();
        Collections.reverse(bookLoans);
        return bookLoans.stream().limit(100).collect(Collectors.toList());
    }

    //Hämtar alla lån idag
    @Override
    public List<BookLoans> loansToday() {
        List<BookLoans> temp = new ArrayList<>();
        List<BookLoans> bookLoans = bookLoanRepoInterface.findAll();
        LocalDate today = LocalDate.now();
        for (BookLoans b : bookLoans) {
            if (b.getDateOfBookLoanDay().equals(today)) {
                temp.add(b);
            }
        }
        return temp;
    }

    //Hämtar alla lån senaste veckan från idag
    @Override
    public List<BookLoans> loansWeek() {
        List<BookLoans> temp = new ArrayList<>();
        List<BookLoans> bookLoans = bookLoanRepoInterface.findAll();
        LocalDate today = LocalDate.now();
        LocalDate sevenDaysBack = today.minusDays(7);
        for (BookLoans b : bookLoans) {
            if (b.getDateOfBookLoanDay().isAfter(sevenDaysBack)) {
                temp.add(b);
            }
        }
        return temp;
    }

    //Hämtar alla lån för en viss månad och då menar jag för t ex mars(3) och utgår alltså inte från idag
    //Skicka med intar för den år och månad, dvs år = 2022, mars = 3
    @Override
    public List<BookLoans> loansMonth(int year, int month) {
        List<BookLoans> temp = new ArrayList<>();
        List<BookLoans> bookLoans = bookLoanRepoInterface.findAll();
        int day = 1;
        LocalDate monthStart = LocalDate.of(year, month, day);
        LocalDate nextMonth = LocalDate.of(year, month + 1, day);
        for (BookLoans b : bookLoans) {
            if ((b.getDateOfBookLoanDay().isEqual(monthStart) || b.getDateOfBookLoanDay().isAfter(monthStart))
                    && b.getDateOfBookLoanDay().isBefore(nextMonth)) {
                temp.add(b);
            }
        }
        return temp;
    }

    //Hämtar SENA lån och sätter status LATE
    @Override
    public List<BookLoans> lateLoans() {
        List<BookLoans> temp = new ArrayList<>();
        List<BookLoans> bookLoans = bookLoanRepoInterface.findAll();
        LocalDate today = LocalDate.now();
        for (BookLoans b : bookLoans) {
            if (b.getDateOfBookReturn().isBefore(today)) {
                Book book = bookRepository.findBookById(b.getBookId());
                if (!book.getStatus().equals(BookStatus.LATE)) {
                    book.setStatus(BookStatus.LATE);
                    bookRepository.save(book);
                }
                temp.add(b);
            }
        }
        return temp;
    }

    //Hämtar alla lån av en viss titel
    @Override
    public List<BookLoans> allLoansOfTitle(String title) {
        List<BookLoans> temp = new ArrayList<>();
        List<BookLoans> bookLoans = bookLoanRepoInterface.findAll();
        for (BookLoans b : bookLoans) {
            if (b.getTitle().equals(title)) {
                temp.add(b);
            }
        }
        return temp;
    }
}