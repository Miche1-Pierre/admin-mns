package com.mns.admin.config;

import com.mns.admin.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeRequests(auth -> auth
                        .requestMatchers(
                                "/api/auth/login",
                                "/api/auth/logout",
                                "/api/auth/verify-email",
                                "/api/auth/candidature",
                                "/api/auth/change-password",
                                "/api/dashboard",
                                "/api/dashboard/widgets",
                                "/api/dashboard/absences",
                                "/api/dashboard/candidatures",
                                "/api/dashboard/users",
                                "/api/dashboard/documents",
                                "/api/dashboard/justificatifs",
                                "/api/dashboard/messages",
                                "/api/dashboard/profil",
                                "/api/dashboard/modules/absences",
                                "/api/dashboard/modules/candidatures",
                                "/api/dashboard/messaging",
                                "/api/dashboard/users",
                                "/api/absences",
                                "/api/absences/",
                                "/api/formations",
                                "/api/formations/formations").permitAll()
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
