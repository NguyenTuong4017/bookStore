package com.beprogrammingbookstore.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.beprogrammingbookstore.bookstore.entity.UserEntity;
import com.beprogrammingbookstore.bookstore.entity.UserRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity currentUser = userRepository.findByUsername(username);

        return User.withUsername(currentUser.getUsername())
                .password(currentUser.getPassword())
                .authorities(currentUser.getRole())
                .build();
    }
}
