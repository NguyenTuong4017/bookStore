
package com.beprogrammingbookstore.bookstore.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.beprogrammingbookstore.bookstore.controller.UserDetailServiceImpl;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {
        @Autowired
        private UserDetailServiceImpl userDetailServiceImpl;

        @Bean
        public SecurityFilterChain configure(HttpSecurity http) throws Exception {
                http
                                .authorizeHttpRequests(authorize -> authorize
                                                .requestMatchers("/h2-console/**").permitAll()
                                                .requestMatchers("/").permitAll()
                                                .requestMatchers("/", "/api/books/**").permitAll()
                                                .anyRequest().authenticated())

                                .formLogin(formlogin -> formlogin
                                                .loginPage("/login")
                                                .defaultSuccessUrl("/booklist", true)
                                                .permitAll())

                                .logout(logout -> logout
                                                .permitAll());

                return http.build();
        }

        @Autowired
        public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
                auth.userDetailsService(userDetailServiceImpl).passwordEncoder(new BCryptPasswordEncoder());
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }

}
