package com.pascadenis.honestee.services;

import com.pascadenis.honestee.entities.Authority;
import com.pascadenis.honestee.repositories.AuthorityRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthorityService {

    private final AuthorityRepository authorityRepository;

    public AuthorityService(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    public void addAuthority(Authority authority) {

        authorityRepository.save(authority);
    }

   public Set<Authority> findByName(String name) {

        return authorityRepository.findByName(name);
   }

    public Set<Authority> findAll() {
       return authorityRepository.findAll()
               .stream()
               .collect(Collectors.toSet());
    }

}
