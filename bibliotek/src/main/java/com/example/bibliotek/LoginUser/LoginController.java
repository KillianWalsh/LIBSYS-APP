package com.example.bibliotek.LoginUser;

import com.example.bibliotek.Borrower.Borrower;
import com.example.bibliotek.Borrower.BorrowerRepositoryInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityNotFoundException;

@Controller
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/")
public class LoginController {

    private final BorrowerRepositoryInterface borrowerRepositoryInterface;

    @GetMapping("home")
    public ModelAndView home() {
        return new ModelAndView("home");
    }

    @GetMapping("LoginUser")
    public ModelAndView login() {
        return new ModelAndView("LoginUser");
    }

    @GetMapping("LoginError")
    public ModelAndView loginError() {
        return new ModelAndView("LoginError");
    }

    @GetMapping("news")
    public ModelAndView news() {
        return new ModelAndView("news");
    }

    @GetMapping("searchBook")
    public ModelAndView searchBook() {
        return new ModelAndView("searchBook");
    }

    @GetMapping("RoomBooking")
    public ModelAndView roomBooking() {
        return new ModelAndView("RoomBooking");
    }

    @GetMapping("contact")
    public ModelAndView contact() {
        return new ModelAndView("contact");
    }

    @GetMapping("UserProfil")
    public ModelAndView userProfil() {
        return new ModelAndView("UserProfil");
    }

    @GetMapping("Logout")
    public ModelAndView logout() {
        return new ModelAndView("Logout");
    }

    @GetMapping("saveBook")

    public ModelAndView saveBook() {
        return new ModelAndView("saveBook");
    }

    @GetMapping("AddUser")
    public ModelAndView addUser() {
        return new ModelAndView("AddUser");
    }

    @GetMapping("SearchUser")
    public ModelAndView findUser() {
        return new ModelAndView("SearchUser");
    }

    @GetMapping("WishList")
    public ModelAndView wishList() {
        return new ModelAndView("WishList");
    }

    @GetMapping("WishListAdmin")
    public ModelAndView wishListAdmin() {
        return new ModelAndView("WishListAdmin");
    }

    @GetMapping("Rapport")
    public ModelAndView rapport() {
        return new ModelAndView("Rapport");
    }

    @GetMapping("userRegistration")
    public ModelAndView userRegistration() {
        return new ModelAndView("userRegistration");
    }


    @RequestMapping(value = "username", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Borrower> currentEmail() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            final String currentPrincipalName = authentication.getName();
            System.out.println();
            System.err.println("Authentication: " + authentication);
            System.out.println();
            System.err.println("Principal: " + authentication.getPrincipal());
            System.out.println();

            System.err.println(currentPrincipalName);

            var found = borrowerRepositoryInterface.findByEmail(currentPrincipalName)
                    .orElseThrow(() -> new EntityNotFoundException("not found"));

            System.err.println(found);

            return new ResponseEntity<>(found, HttpStatus.OK);

        }
//        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(new Borrower(
                0L,
                "test",
                "test",
                "test",
                "test",
                "test"),
                HttpStatus.OK);
    }

}
