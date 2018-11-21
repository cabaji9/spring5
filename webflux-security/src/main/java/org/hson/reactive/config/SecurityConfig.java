package org.hson.reactive.config;

import org.hson.reactive.model.User;
import org.hson.reactive.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 * Created by Hyun Woo Son on 11/19/18
 **/
@Configuration
@EnableWebFluxSecurity
public class SecurityConfig  {


    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http){
        return http.csrf().disable().authorizeExchange().pathMatchers("/user/*","/route/*")
                .hasRole("USER").anyExchange().permitAll().and().httpBasic().and().build();
    }


    @Bean
    public ReactiveUserDetailsService userDetailsService(UserRepository userRepository) {
        return username -> userRepository.findUserByName(username).map(User::getDetails);
    }

}
