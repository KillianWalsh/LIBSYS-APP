package com.example.bibliotek.LoginUser;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginUserServiceImpl implements UserDetailsService {

    private final LoginUserRepository loginUserRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        final LoginUser loginUser = loginUserRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User with email: " + email + " not found"));


        return User.withUsername(email)
                .password(loginUser.getPassword())
                .authorities(String.valueOf(loginUser.getRole())).build();
    }
}
