package com.traffsys.security_config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.traffsys.jwtFilter.jwtFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final jwtFilter filter;

    public SecurityConfig(jwtFilter filter) {
        this.filter = filter;
    }

    @Bean
    public SecurityFilterChain security(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())

            .authorizeHttpRequests(auth -> auth

                // public APIs
                .requestMatchers("/auth/**").permitAll()

                // admin APIs
                .requestMatchers("/home/**").hasAnyRole("USER","ADMIN")
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/product/**").hasRole("ADMIN")
                .requestMatchers("/role/**").permitAll()
                .requestMatchers("stage/").hasRole("ADMIN")
                .requestMatchers("/defect/**").hasRole("ADMIN")
                .requestMatchers("/inspection/**").hasRole("ADMIN")
               .requestMatchers("/lotbatch/**").hasRole("ADMIN")
               .requestMatchers("/QCResultMaster/**").hasRole("ADMIN")
                
                // user APIs
                .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")
						/* .requestMatchers("/product/**").hasAnyRole("ADMIN", "USER") */
                

                // remaining APIs
                .anyRequest().authenticated()
            )

            .sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            );

        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}