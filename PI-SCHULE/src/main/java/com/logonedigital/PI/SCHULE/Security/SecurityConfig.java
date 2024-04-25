package com.logonedigital.PI.SCHULE.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    private static final String[] SADMIN_SECURE_URLs= {
            "/api/v1/**/**"
    };
    private static final String[] ENS_SECURE_URLs= {
            "/api/v1/ficheDePresence/add",
            "/api/v1/ficheDePresence/edit",
            "/api/v1/ficheDePresence/delete",
            "/api/v1/notes/add",
            "/api/v1/notes/edit",
            "/api/v1/devoirs/add",
            "/api/v1/devoirs/edit",
            "/api/v1/reclamations/treated",
            "/api/v1/planifications/getAllForTeacher/**",
            "/api/v1/options/getAllForTeacher/**",
            "/api/v1/appartenances/getByOption/**",
            "/api/v1/appartenances/getAllByAnnee/**"
    };
//    private static final String[] ADMIN_SECURE_URLs = {
//            "/api/v1/enseignants/**",
//            "/api/v1/etudiants/**",
//            "/api/v1/pensionsScolaire/add",
//            "/api/v1/pensionsScolaire/edit",
//            "/api/v1/pensionsScolaire/delete",
//            "/api/v1/emploisDuTemps/add",
//            "/api/v1/emploisDuTemps/edit",
//            "/api/v1/emploisDuTemps/delete",
//            "/api/v1/anneeAcademique/add",
//            "/api/v1/anneeAcademique/edit",
//    };
//    private static final String[] UN_SECURE_URLs ={
//            "/api/v1/emploisDuTemps/getAll",
//            "/api/v1/pensionsScolaire/getAll",
//            "/api/v1/ficheDePresence/getAll",
//            "/api/v1/notes/getAll",
//            "/api/v1/emploisDuTemps/detail/**",
//            "/api/v1/pensionsScolaire/detail/**",
//            "/api/v1/ficheDePresence/detail/**",
//            "/api/v1/notes/detail/**"
//    };

    @Bean
    public PasswordEncoder encoder (){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public org.springframework.security.core.userdetails.UserDetailsService userDetailsService(){
        return new UserDetailsService();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();

        authenticationProvider.setPasswordEncoder(encoder());
        authenticationProvider.setUserDetailsService(userDetailsService());

        return authenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
         http.authorizeHttpRequests(auth-> {
//             auth.requestMatchers(ADMIN_SECURE_URLs).hasAuthority("ADMIN");
             auth.requestMatchers(ENS_SECURE_URLs).hasAuthority("ENSEIGNANT");
//             auth.requestMatchers(UN_SECURE_URLs).hasAnyAuthority("ADMIN","ETUDIANT","ENSEIGNANT");
             auth.anyRequest().permitAll();
        });

        http.csrf(csrf -> csrf.disable())
                .sessionManagement(session ->
                        session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS))
                .httpBasic(it -> {});
        return http.build();
    }
}
