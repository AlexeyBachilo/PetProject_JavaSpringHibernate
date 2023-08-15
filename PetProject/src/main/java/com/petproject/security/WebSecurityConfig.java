package com.petproject.security;

import com.petproject.service.UserService;
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
    UserService userService;

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, MvcRequestMatcher.Builder mvc) throws Exception{
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers(mvc.pattern("/petproject/registration")).anonymous()
                        .requestMatchers(mvc.pattern("/petproject/admin/**")).hasRole("ADMIN")
                        .requestMatchers(mvc.pattern("/petproject/admin/tasks"),mvc.pattern("/petproject/admin/tasks/**")).hasAnyRole("MODERATOR", "ADMIN")
                        .requestMatchers(mvc.pattern("/petproject/user/tasks"),mvc.pattern("/petproject/user/tasks/**")).hasAnyRole("USER", "MODERATOR", "ADMIN")
                        .requestMatchers(mvc.pattern("/petproject/")).permitAll()
                        .requestMatchers(mvc.pattern("/petproject/resources/**")).permitAll()
                        .anyRequest().authenticated())
                .formLogin((login) -> login
                        .loginPage("/petproject/login")
                        .loginProcessingUrl("/petproject/login")
                        .defaultSuccessUrl("/petproject/")
                        .failureUrl("/petproject/login?error=true")
                        .permitAll()
                ).logout((logout) -> logout
                        .logoutRequestMatcher(mvc.pattern("/petproject/logout"))
                        .clearAuthentication(true)
                        .deleteCookies("JSESSIONID")
                        .invalidateHttpSession(true)
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
                .userDetailsService(userService)
                .passwordEncoder(passwordEncoder());
    }
}
