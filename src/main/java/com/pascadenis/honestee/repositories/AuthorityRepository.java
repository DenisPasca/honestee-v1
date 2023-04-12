package com.pascadenis.honestee.repositories;

import com.pascadenis.honestee.entities.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface AuthorityRepository extends JpaRepository<Authority,Long> {

    Set<Authority> findByName(String name);

}
