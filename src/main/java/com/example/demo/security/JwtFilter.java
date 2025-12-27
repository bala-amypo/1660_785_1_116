// package com.example.demo.security;

// import jakarta.servlet.FilterChain;
// import jakarta.servlet.ServletException;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;
// import org.springframework.stereotype.Component;
// import org.springframework.web.filter.OncePerRequestFilter;

// import java.io.IOException;

// @Component
// public class JwtFilter extends OncePerRequestFilter {

//     private final JwtTokenProvider jwtTokenProvider;

//     public JwtFilter(JwtTokenProvider jwtTokenProvider) {
//         this.jwtTokenProvider = jwtTokenProvider;
//     }

//     @Override
//     protected void doFilterInternal(
//             HttpServletRequest request,
//             HttpServletResponse response,
//             FilterChain filterChain)
//             throws ServletException, IOException {

//         String path = request.getRequestURI();

//         if (path.contains("/auth")
//         || path.contains("/swagger-ui")
//         || path.contains("/v3/api-docs")) {

//             filterChain.doFilter(request, response);
//             return;
//         }

//         String header = request.getHeader("Authorization");

//         if (header == null || !header.startsWith("Bearer ")) {
//             //response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//                 filterChain.doFilter(request, response);

//             return;
//         }

//         String token = header.substring(7);

//         if (!jwtTokenProvider.validateToken(token)) {
//             //response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//             filterChain.doFilter(request, response);
//             return;
//         }

//         filterChain.doFilter(request, response);
//     }
// }


package com.example.demo.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.stream.Collectors;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    public JwtFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        String path = request.getRequestURI();

        if (path.contains("/auth")
                || path.contains("/swagger-ui")
                || path.contains("/v3/api-docs")) {

            filterChain.doFilter(request, response);
            return;
        }

        String header = request.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = header.substring(7);

        if (!jwtTokenProvider.validateToken(token)) {
            filterChain.doFilter(request, response);
            return;
        }

        // ✅ SET AUTHENTICATION (THIS WAS MISSING)
        String username = jwtTokenProvider.getUsername(token);
        var roles = jwtTokenProvider.getRoles(token);

        var authorities = roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(
                        username, null, authorities);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }
}
