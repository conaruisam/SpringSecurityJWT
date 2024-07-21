package com.example.security;

import com.example.security.filters.JwtAuthenticationFilter;
import com.example.security.filters.JwtAuthorizationFilter;
import com.example.security.jwt.JWTUtils;
import com.example.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true) // Habilitamos las anotaciones de Spring Security para nuestros controllers
public class SecurityConfig {

    @Autowired
    private JWTUtils jwtUtils;
    @Autowired
    private JwtAuthorizationFilter authorizationFilter;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, AuthenticationManager authenticationManager) throws Exception {

        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(jwtUtils);
        jwtAuthenticationFilter.setAuthenticationManager(authenticationManager);
        jwtAuthenticationFilter.setFilterProcessesUrl("/login");

        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable) // Seguridad de formularios.
                .authorizeHttpRequests( auth -> {
                    auth.requestMatchers("/hello").permitAll(); // Este endpoint sería público
                    //auth.requestMatchers("/accessAdmin").hasRole("ADMIN"); // Este endpoint
                    auth.anyRequest().authenticated(); // Mientras que el resto necesitaría autenticación
                })
                .sessionManagement(ses -> { // No hay sesión
                    ses.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                })
                //.httpBasic(Customizer.withDefaults()) // El logeo basic está activado.
                .addFilter(jwtAuthenticationFilter)
                .addFilterBefore(authorizationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    // Creo un usuario en memoria.
 /*
    @Bean
    UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

        manager.createUser(User.withUsername("isam")
                .password("1234")
                .roles()
                .build());

        return manager;
    }
 */

    @Bean
    PasswordEncoder passwordEncoder() {
        //return NoOpPasswordEncoder.getInstance(); para mientras...
        return new BCryptPasswordEncoder();
    }
    // Objeto que se encarga de la autenticación de los usuarios
    @Bean
    AuthenticationManager authenticationManager(HttpSecurity httpSecurity, PasswordEncoder passwordEncoder) throws Exception {
        return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder)
                .and().build();

    }
}
