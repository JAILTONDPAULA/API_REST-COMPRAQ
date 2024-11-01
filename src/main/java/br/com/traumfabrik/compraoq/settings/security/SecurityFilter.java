package br.com.traumfabrik.compraoq.settings.security;

import br.com.traumfabrik.compraoq.repositories.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = requestToken(request);
        if(token != null) {
            String email = tokenService.decodeToken(token);
            UserDetails userDetails = userRepository.findByUserName(email);
            var tokeAuthentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(tokeAuthentication);
        }
        filterChain.doFilter(request,response);
    }

    private String requestToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if(token != null) {
            return token.replace("Bearer ","");
        }
        return null;
    }
}
