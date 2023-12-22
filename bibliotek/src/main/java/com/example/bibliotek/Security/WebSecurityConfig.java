package com.example.bibliotek.Security;

import com.example.bibliotek.Borrower.Borrower;
import com.example.bibliotek.LoginUser.LoginUserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static com.example.bibliotek.LoginUser.Role.*;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
//@EnableWebMvc
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

// admin = /libsys/management/admin
// ska kunna allt

// librarian = /libsys/management
//    sköta borrower

// borrower = /borrower
//    kunna låna bok

// bookloans = /bookloans
//

// book = /book
//


    //    private final CustomAuthenticationProvider customAuthenticationProvider;
    private final LoginUserServiceImpl loginUserServiceImpl;
    private final BCryptPasswordEncoder bcryptPasswordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.cors().and().csrf().disable().cors();

        http
                .cors().and().csrf().disable()
                .headers().frameOptions().sameOrigin()
                .and()
                .authorizeRequests()
                .antMatchers("/news", "/home", "/resources/**", "/static/**", "/book/findbytitle/**", "/book/findbyauthor/**", "/book/findbygenre/**", "/book/findbookbyid/**")
                .permitAll()
                .antMatchers("/libsys/management/admin/**").hasAnyAuthority(ADMIN.name())
                .antMatchers("/libsys/management/**").hasAuthority(LIBRARIAN.name())
//                .antMatchers("/book/findbookbyid/**").hasRole(BORROWER.name())
                .antMatchers("/book/save").hasAnyAuthority(ADMIN.name(), LIBRARIAN.name())
                .antMatchers("/RoomBooking/*").hasAnyAuthority(ADMIN.name(), LIBRARIAN.name()) //added from Lisa
                .antMatchers("/bookloan/save").hasAnyAuthority(ADMIN.name(), LIBRARIAN.name(), BORROWER.name())
//                .antMatchers("/book/findbytitle/**").hasAnyRole(ADMIN.name(), LIBRARIAN.name(), BORROWER.name())
                .and()
                .formLogin()
                .loginPage("/LoginUser").permitAll()
                .defaultSuccessUrl("/home", true)
                .failureUrl("/LoginError").permitAll()
                .and()
                .logout()
                .logoutUrl("/Logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/home").permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(loginUserServiceImpl).passwordEncoder(bcryptPasswordEncoder);
    }
}
