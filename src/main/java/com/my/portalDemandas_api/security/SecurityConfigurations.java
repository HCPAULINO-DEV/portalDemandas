package com.my.portalDemandas_api.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfigurations {

    @Autowired
    private SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable()) // Desativa CSRF
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Stateless
                .authorizeHttpRequests(req -> {
                    //Permitir login
                    req.requestMatchers(HttpMethod.POST, "/login").permitAll();

                    // Permissões para USER
                    req.requestMatchers(HttpMethod.GET, "/tarefas/**").hasRole("USER"); // USER pode ver suas tarefas
                    req.requestMatchers(HttpMethod.POST, "/tarefas/**").hasRole("USER"); // USER pode criar tarefas

                    // Permissões para ADMIN
                    req.requestMatchers(HttpMethod.GET, "/usuarios/**").hasRole("ADMIN"); // ADMIN pode ver qualquer usuário
                    req.requestMatchers(HttpMethod.GET, "/tarefas/**").hasRole("ADMIN"); // ADMIN pode ver qualquer tarefa
                    req.requestMatchers(HttpMethod.POST, "/tarefas/**").hasRole("ADMIN"); // ADMIN pode criar tarefas
                    req.requestMatchers(HttpMethod.PUT, "/usuarios/**").hasRole("ADMIN"); // ADMIN pode atualizar usuários
                    req.requestMatchers(HttpMethod.PUT, "/tarefas/**").hasRole("ADMIN"); // ADMIN pode atualizar tarefas
                    req.requestMatchers(HttpMethod.DELETE, "/usuarios/**").hasRole("ADMIN"); // ADMIN pode deletar usuários
                    req.requestMatchers(HttpMethod.DELETE, "/tarefas/**").hasRole("ADMIN"); // ADMIN pode deletar tarefas
                })
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class) // Adiciona filtro personalizado
                .build();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager(); // Gerenciador de autenticação
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Codificador de senhas seguro
    }
}



