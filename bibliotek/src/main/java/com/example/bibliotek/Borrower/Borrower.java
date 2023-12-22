package com.example.bibliotek.Borrower;

import com.example.bibliotek.LoginUser.LoginUser;
import com.example.bibliotek.LoginUser.Role;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Borrower extends LoginUser {


    private Long id;
    @Column(name = "first_name")
    private String firstname;
    @Column(name = "last_name")
    private String lastname;
    @Column(name = "person_number")
    private String socialNumber;
    //    @NotEmpty(message = "Email can not be empty")
    @Transient
    @Column(name = "email", unique = true)
    private String email;
    @Column(name = "password")
    private String password;
//    @Enumerated(EnumType.STRING)
//    @Column(name = "role")
//    private Role role;

//    public Borrower(Long id, String email, String password, Role role, Boolean isAccountNonExpired, Boolean isAccountNonLocked,
//                    Boolean isCredentialsNonExpired, Boolean isEnabled, String firstname, String lastname, String socialNumber,
//                    String email1, String password1) {
//        super(id, email, password, role, isAccountNonExpired, isAccountNonLocked, isCredentialsNonExpired, isEnabled);
//        this.id = id;
//        this.firstname = firstname;
//        this.lastname = lastname;
//        this.socialNumber = socialNumber;
//        this.email = email1;
//        this.password = password1;
//    }


//    public Borrower(String firstname, String lastname, String socialNumber, String email, String password) {
//        this.firstname = firstname;
//        this.lastname = lastname;
//        this.socialNumber = socialNumber;
//        this.email = email;
//        this.password = password;
//    }
}
