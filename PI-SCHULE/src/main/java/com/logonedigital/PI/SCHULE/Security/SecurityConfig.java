package com.logonedigital.PI.SCHULE.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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

    private static final String[] UN_SECURE_URLs = {
            "/api/v1/users/detail/**",
            "/api/v1/users/edit/**",
    };
    private static final String[] SCHOOL_SECURE_URLs = {
            "/api/v1/anneeAcademique/getAll/**",
            "/api/v1/planifications/getAllByOption/**",
            "/api/v1/devoirs/findByOption/**",
            "/api/v1/notes/getAll/**",
            "/api/v1/matieres/getAll/**",
            "/api/v1/matieres/findAll/**",
            "/api/v1/appartenances/getByOption/**",
            "/api/v1/appartenances/getAllByAnnee/**"
    };

    private static final String[] SADMIN_SECURE_URLs = {
            "/api/v1/ecoles/**",
            "/api/v1/users/add",
            "/api/v1/users/search",
            "/api/v1/super/**"
    };
    private static final String[] ENS_SECURE_URLs = {
            "/api/v1/ficheDePresence/add",
            "/api/v1/reclamations/findByEnseignant/**",
            "/api/v1/planifications/getAllForTeacher/**",
            "/api/v1/options/getAllForTeacher/**",
    };
    private static final String[] ADMIN_SECURE_URLs = {
            "/api/v1/enseignants/add",
            "/api/v1/enseignants/edit/**",
            "/api/v1/enseignants/getAllByEcole/**",
            "/api/v1/etudiants/add",
            "/api/v1/etudiants/edit/**",
            "/api/v1/etudiants/getAllByEcole/**",
            "/api/v1/tarifs/**",
            "/api/v1/planifications/add",
            "/api/v1/planifications/edit",
            "/api/v1/paiements/add",
            "/api/v1/paiements/getById/**",
            "/api/v1/options/add",
            "/api/v1/options/edit/**",
            "/api/v1/options/delete/**",
            "/api/v1/options/findAll/**",
            "/api/v1/filieres/**",
            "/api/v1/classes/**",
            "/api/v1/matieres/add",
            "/api/v1/matieres/edit/**",
            "/api/v1/matieres/delete/**",
            "/api/v1/reclamations/findByEcole",
            "/api/v1/cycles/**",
            "/api/v1/appartenances/add",
            "/api/v1/appartenances/edit/**",
            "/api/v1/admins/**",
            "/api/v1/ficheDePresence/add",
            "/api/v1/ficheDePresence/justify",
            "/api/v1/ficheDePresence/getAll/**",
            "/api/v1/anneeAcademique/add",
            "/api/v1/anneeAcademique/edit/**",
    };
    private static final String[] ETD_SECURE_URLs = {
            "/api/v1/ficheDePresence/findByEtd/**",
            "/api/v1/reclamations/findByEtudiant/**",
            "/api/v1/reclamations/add",
            "/api/v1/devoirs/findByEtudiant/**",
    };
    private static final String[] ADMIN_AND_ENS_SECURE_URLs = {
            "/api/v1/notes/add",
            "/api/v1/notes/edit",
            "/api/v1/devoirs/add",
            "/api/v1/devoirs/edit/**",
            "/api/v1/reclamations/treated",
            "/api/v1/enseignant/detail/**"
    };
    private static final String[] ADMIN_AND_ETD_SECURE_URLs = {
            "/api/v1/etudiants/detailWithMatricule/**",
            "/api/v1/etudiants/detailWithEmail/**",
            "/api/v1/paiements/detail/**",
    };

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public org.springframework.security.core.userdetails.UserDetailsService userDetailsService() {
        return new UserDetailsService();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();

        authenticationProvider.setPasswordEncoder(encoder());
        authenticationProvider.setUserDetailsService(userDetailsService());

        return authenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf()
                .disable()
                .authorizeHttpRequests(auth -> {
                            auth.requestMatchers(ADMIN_SECURE_URLs).hasAuthority("ADMIN");
                            auth.requestMatchers(ENS_SECURE_URLs).hasAuthority("ENSEIGNANT");
                            auth.requestMatchers(SADMIN_SECURE_URLs).hasAuthority("SUPER_ADMIN");
                            auth.requestMatchers(ETD_SECURE_URLs).hasAuthority("ETUDIANT");
                            auth.requestMatchers(SCHOOL_SECURE_URLs).hasAnyAuthority("ETUDIANT", "ENSEIGNANT", "ADMIN");
                            auth.requestMatchers(ADMIN_AND_ENS_SECURE_URLs).hasAnyAuthority("ADMIN", "ENSEIGNANT");
                            auth.requestMatchers(ADMIN_AND_ETD_SECURE_URLs).hasAnyAuthority("ADMIN", "ETUDIANT");
                            auth.requestMatchers(UN_SECURE_URLs).hasAnyAuthority("ADMIN", "ETUDIANT","SUPER_ADMIN","ENSEIGNANT");
                            auth.requestMatchers( "/api/v1/auth/login").permitAll();
                            try {
                                auth.anyRequest().authenticated().and().httpBasic();
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }
                )
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS);
        http.httpBasic();
        return http.build();

    }

}