package com.project.movie_book_review.configuration;

import com.project.movie_book_review.entity.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    private final String ADMIN_AUTH="ADMIN";
    private final String USER_AUTH="USER";

    @Bean
    public PasswordEncoder getMyPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(csrf -> csrf.disable())//***csrf needs to be disabled if directly hitting POST api from unknown sources
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/movie/add").hasAuthority(ADMIN_AUTH)
                        .requestMatchers("/**").permitAll());

        return httpSecurity.build();
    }
}
