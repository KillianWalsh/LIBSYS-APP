//package com.example.bibliotek.Security;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.stereotype.Component;
//
//@Component
//@RequiredArgsConstructor
//public class CustomAuthenticationProvider implements AuthenticationProvider {
//
//
//    private final UserDetailsService userDetailsService;
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        String email = authentication.getName();
//        String password = authentication.getCredentials().toString();
//
//
//        UserDetails user = userDetailsService.loadUserByUsername(email);
//        if (user == null){
//            throw new BadCredentialsException("Wrong login information");
//        } else if (!user.getPassword().equals(password)){
//            throw new BadCredentialsException("Wrong login information");
//        }
//
//        return new UsernamePasswordAuthenticationToken(email, password, user.getAuthorities());
//
//    }
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//        return authentication.equals(UsernamePasswordAuthenticationToken.class);
//    }
//}
