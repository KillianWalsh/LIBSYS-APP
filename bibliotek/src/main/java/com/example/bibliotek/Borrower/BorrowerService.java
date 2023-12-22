package com.example.bibliotek.Borrower;


import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;

import static com.example.bibliotek.LoginUser.Role.*;

@Service
@RequiredArgsConstructor
public class BorrowerService implements BorrowerServiceInterface {

    private final BorrowerRepositoryInterface borrowerRepositoryInterface;
    private final PasswordEncoder passwordEncoder;

    //public static ArrayList<Borrower> borrowerArrayList = new ArrayList<>();
//
//    public BorrowerService(BorrowerRepositoryInterface borrowerRepositoryInterface) {
//        this.borrowerRepositoryInterface = borrowerRepositoryInterface;
    //hämta alla låntagare från databas och lägg i arraylist
        /*if((borrowerArrayList = this.findAll()) != null){
            for (Borrower borrower: borrowerArrayList){
                System.out.println(borrower.toString());
            }
        }*/
//    }

    @Override
    public void save(Borrower borrower) {
        borrower.setRole(BORROWER);
        borrower.setIsAccountNonExpired(true);
        borrower.setIsAccountNonLocked(true);
        borrower.setIsCredentialsNonExpired(true);
        borrower.setIsEnabled(true);
        borrower.setPassword(passwordEncoder.encode(borrower.getPassword()));
        borrowerRepositoryInterface.save(borrower);
    }

//    @Override
//    public Borrower save(Borrower borrower) {
//        borrower.setRole(BORROWER);
//        borrower.setIsAccountNonExpired(true);
//        borrower.setIsAccountNonLocked(true);
//        borrower.setIsCredentialsNonExpired(true);
//        borrower.setIsEnabled(true);
//        borrower.setPassword(passwordEncoder.encode(borrower.getPassword()));
//        return borrowerRepositoryInterface.save(borrower);
//    }

    @Override
    public Borrower register(Borrower borrower) {
        final boolean existingBorrower = borrowerRepositoryInterface.findByEmail(borrower.getEmail()).isPresent();

        if (existingBorrower) {
            throw new EntityExistsException("Email: " + borrower.getEmail() + " already taken");
        }
//        final boolean nameValidation = borrower.getFirstname().equals("");
//        final boolean surnameValidation = borrower.getLastname().equals("");
//        final boolean emailValidation = borrower.getEmail().equals("");
//        final boolean personnumberValidation = borrower.getSocialNumber().equals("");
//        final boolean passwordValidation = borrower.getPassword().equals("");
//        if (nameValidation || surnameValidation){
//            throw new IllegalArgumentException("Firstname and/or lastname can't be empty");
//        }
//        if (emailValidation){
//            throw new IllegalArgumentException("Email can't be empty");
//        }
//        if (personnumberValidation){
//            throw new IllegalArgumentException("Personnumber can't be empty");
//        }
//        if (passwordValidation){
//            throw new IllegalArgumentException("Password can't be empty");
//        }

        borrower.setFirstname(borrower.getFirstname());
        borrower.setLastname(borrower.getLastname());
        borrower.setEmail(borrower.getEmail());
        borrower.setSocialNumber(borrower.getSocialNumber());
        borrower.setPassword(borrower.getPassword());
        borrower.setIsAccountNonExpired(true);
        borrower.setIsAccountNonLocked(true);
        borrower.setIsCredentialsNonExpired(true);
        borrower.setIsEnabled(true);
        borrower.setPassword(passwordEncoder.encode(borrower.getPassword()));
        borrower.setRole(BORROWER);

        return borrowerRepositoryInterface.save(borrower);
    }

    @Override
    public Borrower findByEmail(String email) {
        return borrowerRepositoryInterface.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Borrower with email " + email + " not found"));
    }

    public Borrower findById(Long id) {
        return borrowerRepositoryInterface.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Borrower not found"));
    }

    @Override
    public ArrayList<Borrower> findAll() {
        return (ArrayList<Borrower>) borrowerRepositoryInterface.findAll();
    }
}
