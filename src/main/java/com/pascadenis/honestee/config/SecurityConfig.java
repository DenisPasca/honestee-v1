package com.pascadenis.honestee.config;

import com.pascadenis.honestee.services.JpaUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {


        return httpSecurity.httpBasic()
                .and()
                .authorizeRequests()
                .mvcMatchers("/admin/**").hasAuthority("admin")
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/do-login")
                .defaultSuccessUrl("/index")
                .and()
                .logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout")
                .permitAll()
                .and().build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {

           return NoOpPasswordEncoder.getInstance();
    }


//    @Bean
//    public UserDetailsService userDetailsService() {
//
//        var uds = new InMemoryUserDetailsManager();
//
//        var user1= User.withUsername("12")
//                .password("34f")
//                .authorities("read")
//                .build();
//
//        uds.createUser(user1);
//
//        return uds;
//
//    }

}
