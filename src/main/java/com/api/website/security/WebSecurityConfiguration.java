package com.api.website.security;

import com.api.website.controller.RoleName;
import com.api.website.security.component.CustomAuthenticationProvider;
import com.api.website.security.filter.JwtRequestFilter;
import com.api.website.security.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Configuration
@Order(SecurityProperties.BASIC_AUTH_ORDER - 2)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    private CustomAuthenticationProvider authProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;

//    private BCryptPasswordEncoder bCryptPasswordEncoder;

//    @Autowired
//    private CsrfTokenFilter csrfTokenFilter;

    @Value("${security.enable.csrf}")
    private boolean csrfEnabled;

    @Value("${security.enable.cors}")
    private boolean corsEnabled;

    private String GET_REQUESTS_ROLE_USER = "";
    private String GET_REQUESTS_PERMIT_ALL = "/authenticate";
    private String POST_REQUESTS_ROLE_ADMIN = "/users/create";

    public WebSecurityConfiguration() {
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.httpBasic().authenticationEntryPoint(new AuthenticationEntryPoint());
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().antMatchers(HttpMethod.GET, GET_REQUESTS_PERMIT_ALL).permitAll();   // "/test/*", "/test/login",
        http.authorizeRequests().antMatchers(HttpMethod.POST, POST_REQUESTS_ROLE_ADMIN).hasAnyAuthority(RoleName.ROLE_ADMIN.toString());
        http.authorizeRequests().anyRequest().authenticated();


        if (corsEnabled) {
            http.cors().configurationSource(corsConfigurationSource());
        }

        if (csrfEnabled) {
            http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
        } else {
            http.csrf().disable();
        }
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService).passwordEncoder(passwordEncoder);
        auth.authenticationProvider(authProvider);
        super.configure(auth);
    }

    @Bean
    UrlBasedCorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "OPTIONS", "POST"));
        List<String> headerList = new ArrayList<String>();
        headerList.add("x-auth-token");
        headerList.add("x-requested-with");
        headerList.add("x-xsrf-token");
        configuration.setAllowedHeaders(headerList);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//
////        return NoOpPasswordEncoder.getInstance();
//    }


}
