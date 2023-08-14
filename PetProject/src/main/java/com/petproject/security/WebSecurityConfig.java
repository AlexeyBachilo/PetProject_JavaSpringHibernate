package com.petproject.security;

import com.petproject.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, MvcRequestMatcher.Builder mvc) throws Exception{
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers(mvc.pattern("/registration")).anonymous()
                        .requestMatchers(mvc.pattern("/admin/**")).hasRole("ADMIN")
                        .requestMatchers(mvc.pattern("/admin/tasks/**")).hasRole("MODERATOR")
                        .requestMatchers(mvc.pattern("/user/tasks/**")).hasRole("USER")
                        .requestMatchers(mvc.pattern("/")).permitAll()
                        .requestMatchers(mvc.pattern("/resources/**")).permitAll()
                        .anyRequest().anonymous())
                .formLogin((login) -> login
                        .loginPage("/petproject/login")
                        .loginProcessingUrl("/petproject/authenticateUser")
                        .defaultSuccessUrl("/petproject/")
                        .permitAll()
                ).logout((logout) -> logout
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .logoutSuccessUrl("/login?logout")
                        .permitAll());
        return http.build();
    }

    @Scope("prototype")
    @Bean
    MvcRequestMatcher.Builder mvc(HandlerMappingIntrospector introspector){
        return new MvcRequestMatcher.Builder(introspector);
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth
                .userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder());
    }
}
