package com.pascadenis.honestee.repositories;

import com.pascadenis.honestee.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users,Long> {

    @Query("""
          SELECT u FROM Users u WHERE u.username = :username  
            """)
    Optional<Users> findUserByUsername(String username);



//     @Query("""
//                SELECT u FROM Users u WHERE u.username = :users.username
//
//             """)
//    Users findUsernameByUser(Users users);

    Users findByUsername(String username);


}
