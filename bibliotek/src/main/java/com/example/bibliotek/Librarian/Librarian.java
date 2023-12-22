package com.example.bibliotek.Librarian;

import com.example.bibliotek.LoginUser.LoginUser;
import com.example.bibliotek.LoginUser.Role;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Librarian extends LoginUser {

    @Column(name = "first_name")
    private String firstname;
    @Column(name = "last_name")
    private String lastname;
    @Column(name = "person_number")
    private String socialNumber;
    @Transient
    @Column(name = "email", unique = true)
    private String email;
    @Column(name = "password")
    private String password;
//    @Enumerated(EnumType.STRING)
//    @Column(name = "role")
//    private Role role;

//    public Librarian(String firstname, String lastname, String socialNumber, String email, String password) {
//        this.firstname = firstname;
//        this.lastname = lastname;
//        this.socialNumber = socialNumber;
//        this.email = email;
//        this.password = password;
//    }
}
