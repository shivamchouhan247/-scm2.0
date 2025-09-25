package com.scm.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.scm.service.impl.CustomUserDetailsService;

@Configuration
public class SecurityConfig {
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    // Filter chain custom configuration
    @Bean
    public SecurityFilterChain defaultSecurityConfig(HttpSecurity httpSecurity) throws Exception {

        //Url request permit configuration
        httpSecurity.authorizeHttpRequests(request -> {
            request.requestMatchers("/user/**").authenticated();
            request.anyRequest().permitAll();
        });

        //Form based login configuration
        httpSecurity.formLogin(Customizer.withDefaults());

        return httpSecurity.build();
    }

    //Authentication Provider Configuration 
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(customUserDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
