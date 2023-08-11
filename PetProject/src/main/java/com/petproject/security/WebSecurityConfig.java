package com.petproject.security;

import com.petproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Autowired
    UserService userService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/registration").anonymous()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/admin/tasks/**").hasRole("MODERATOR")
                        .requestMatchers("/user/tasks/**").hasRole("USER")
                        .requestMatchers("/","/resources/**").permitAll()
                        .anyRequest().authenticated())
                .formLogin((cust) -> cust
                        .loginPage("/login")
                        .defaultSuccessUrl("/")
                        .permitAll()).logout((config) -> config
                        .permitAll()
                        .logoutSuccessUrl("/"));
        return http.build();
    }

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder());
    }
}
