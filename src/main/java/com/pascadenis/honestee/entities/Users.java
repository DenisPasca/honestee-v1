package com.pascadenis.honestee.entities;


import lombok.Data;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long id;


    private String username;

    @Size(min = 3,max = 15,message = "Size must be between 3 and 15 characters")
    @Column(name = "first_name")
    private String firstName;

    @Size(min = 3,max = 15,message = "Size must be between 3 and 15 characters")
    @Column(name = "last_name")
    private String lastName;

    @Email
    private String email;


    @NotBlank(message = "mandatory")
    private String password;


    @NotBlank(message = "mandatory")
    @Column(name = "repeat_password")
    private String repeatPassword;

    @OneToOne(mappedBy = "user")
    private ShoppingCart shoppingCart;

    @OneToMany(mappedBy = "user")
    private List<Order> orders;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_authorities",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id"))
    private Set<Authority> authorities;

//    public Set<Authority> getAuthorities() {
//        return authorities;
//    }
//
//    public void setAuthorities(Set<Authority> authorities) {
//        this.authorities = authorities;
//    }
//
//    public long getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }


}
