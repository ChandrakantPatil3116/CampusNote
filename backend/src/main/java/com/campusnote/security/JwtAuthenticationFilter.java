package com.campusnote.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    public JwtAuthenticationFilter(
            JwtService jwtService,
            UserDetailsService userDetailsService) {

        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        final String authHeader =
                request.getHeader("Authorization");

        /*
         * No Authorization header or incorrect format.
         * Continue the request and let Spring Security
         * decide whether authentication is required.
         */
        if (authHeader == null ||
                !authHeader.startsWith("Bearer ")) {

            filterChain.doFilter(request, response);
            return;
        }

        final String jwt =
                authHeader.substring(7);

        try {

            // Extract email from JWT
            final String email =
                    jwtService.extractUsername(jwt);

            /*
             * Only authenticate if:
             * 1. Email exists
             * 2. SecurityContext doesn't already contain authentication
             */
            if (email != null &&
                    SecurityContextHolder
                            .getContext()
                            .getAuthentication() == null) {

                // Load user using CustomUserDetailsService
                UserDetails userDetails =
                        userDetailsService
                                .loadUserByUsername(email);

                // Validate JWT
                if (jwtService.isTokenValid(
                        jwt,
                        userDetails.getUsername())) {

                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(
                                    userDetails,
                                    null,
                                    userDetails.getAuthorities()
                            );

                    authentication.setDetails(
                            new WebAuthenticationDetailsSource()
                                    .buildDetails(request)
                    );

                    // IMPORTANT:
                    // Put authenticated user into SecurityContext
                    SecurityContextHolder
                            .getContext()
                            .setAuthentication(authentication);
                }
            }

        } catch (Exception e) {

            /*
             * Invalid JWT.
             *
             * We don't manually return 403 here.
             * Spring Security will handle the request
             * after the filter chain continues.
             */
        }

        filterChain.doFilter(request, response);
    }
}