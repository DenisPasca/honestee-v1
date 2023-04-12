package com.pascadenis.honestee.services;

import com.pascadenis.honestee.entities.Users;
import com.pascadenis.honestee.repositories.UserRepository;
import com.pascadenis.honestee.security.SecurityUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class JpaUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;



    public JpaUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username)  {


        var u = userRepository.findUserByUsername(username);

        return u.map(SecurityUser::new)
                .orElseThrow(()->new UsernameNotFoundException("Username not found" + username));
    }

    public void addUser(Users user) {


        this.userRepository.save(user);
    }

    public Users findUser(String username) {


      return  userRepository.findByUsername(username);
    }

    public Set<Users> findAll() {

        return  userRepository.findAll().stream().collect(Collectors.toSet());
    }


//    public String username(Users user) {
//
//        return userRepository.findUsernameByUser(user).getUsername();
//
//
//    }

}
