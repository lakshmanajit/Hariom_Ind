package com.traffsys.Authservices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import com.traffsys.Repository.userRepo;
import com.traffsys.entity.user;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private userRepo repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        user user_table = repo.findByUsername(username);

        if (user_table == null) {

            throw new UsernameNotFoundException(
                    "User not found"
            );
        }

        return new org.springframework.security.core.userdetails.User(

                user_table.getUsername(),

                user_table.getPassword(),

                List.of(
                    new SimpleGrantedAuthority(
                        user_table.getRole().getRoleName()
                    )
                )
        );
    }
}