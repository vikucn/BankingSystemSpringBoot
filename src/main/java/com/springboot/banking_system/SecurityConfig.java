package com.springboot.banking_system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.springboot.banking_system.service.UserSecurityService;


@Configuration
public class SecurityConfig {

    @Autowired
    private UserSecurityService userSecurityService;

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf((csrf) -> csrf.disable())
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.GET, "/api/token").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/sign-up").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/hello").authenticated()
                        .requestMatchers(HttpMethod.GET, "/auth/user").authenticated()
                        .requestMatchers(HttpMethod.GET, "/api/executive/hello").hasAuthority("EXECUTIVE")
                        .requestMatchers("/customer/**").hasAuthority("CUSTOMER")
//                   
                     	
                     	
                     	.requestMatchers(HttpMethod.GET, "/api/users-stat").hasAuthority("ADMIN")
//                     	.requestMatchers(HttpMethod.GET, "admin/details").permitAll()
                     	.requestMatchers(HttpMethod.GET, "/admin/employee/update/").permitAll()
                     	.requestMatchers(HttpMethod.GET,"admin/alltransaction").permitAll()
                  
                     	
                     	
//                     	----------------------------
                     	.requestMatchers(HttpMethod.GET, "admin/details").hasAuthority("ADMIN")
                     	.requestMatchers(HttpMethod.GET, "admin/employee/all").hasAuthority("ADMIN")
                     	.requestMatchers(HttpMethod.POST, "/admin/employee/add").hasAuthority("ADMIN")
                     	.requestMatchers(HttpMethod.GET, "admin/employee/{id}").hasAuthority("ADMIN")
                     	.requestMatchers(HttpMethod.POST, "/admin/employeedto/update/{id}").hasAuthority("ADMIN")
                     	.requestMatchers(HttpMethod.GET, "admin/getCustomerAllDetails").hasAuthority("ADMIN")
                     	.requestMatchers(HttpMethod.GET, "/api/CustomerDetails/all").hasAuthority("ADMIN")
                     	
                     	.requestMatchers(HttpMethod.GET, "admin/getCustomerDetailsByAccNum").hasAuthority("ADMIN")
                     	.requestMatchers(HttpMethod.GET, "admin/accountDto").hasAuthority("ADMIN")
                     	.requestMatchers(HttpMethod.GET, "admin/bonds/details").hasAuthority("ADMIN")
                     	.requestMatchers(HttpMethod.GET, "admin/mutualFunds/details").hasAuthority("ADMIN")
                     	.requestMatchers(HttpMethod.GET, "admin/fixedDeposit/details").hasAuthority("ADMIN")
                     	.requestMatchers(HttpMethod.GET, "admin/investment/bond/customer/{id}").hasAuthority("ADMIN")
                     	.requestMatchers(HttpMethod.GET, "admin/investment/mutualFunds/customer/{id}").hasAuthority("ADMIN")
                     	.requestMatchers(HttpMethod.GET, "admin/investment/fixedDeposit/customer/{id}").hasAuthority("ADMIN")
                     	.requestMatchers(HttpMethod.POST, "admin/investment/addBonds").hasAuthority("ADMIN")
                     	.requestMatchers(HttpMethod.POST, "admin/investment/addMutualFunds").hasAuthority("ADMIN")
                     	.requestMatchers(HttpMethod.POST, "admin/investment/addFixedDeposit").hasAuthority("ADMIN")
                     	
//                     	----------------------------
                        .anyRequest().permitAll()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


    @Bean
    BCryptPasswordEncoder getEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userSecurityService);
        authenticationProvider.setPasswordEncoder(getEncoder());
        return authenticationProvider;
    }
}