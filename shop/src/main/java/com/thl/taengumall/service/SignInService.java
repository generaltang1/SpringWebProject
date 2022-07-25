package com.thl.taengumall.service;

import com.thl.taengumall.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;

@Service
@Transactional
@RequiredArgsConstructor
public class SignInService implements UserDetailsService {
    @NotNull
    private final AccountRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var account = repository.findByEmail(email);
        var user = new User(account.getEmail(), account.getPassword(), new ArrayList<GrantedAuthority>());
        return user;
    }
}
