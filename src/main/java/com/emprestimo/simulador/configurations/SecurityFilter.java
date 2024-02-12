package com.emprestimo.simulador.configurations;


import com.emprestimo.simulador.repository.UserAccesPlatformRepository;
import com.emprestimo.simulador.service.AuthenticationService;
import com.emprestimo.simulador.model.UsersAccessPlatform;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private UserAccesPlatformRepository userAccesPlatformRepository;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = extrairToken(request);

        if (token != null) {
            String login = authenticationService.validateToken(token);
            UsersAccessPlatform usuario = (userAccesPlatformRepository.findByLogin(login));

            var authentication = new UsernamePasswordAuthenticationToken(usuario,null,usuario.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    public String extrairToken(HttpServletRequest httpServletRequest){
        var header = httpServletRequest.getHeader("Authorization");
        if(header == null){
            return null;
        }
        if (!header.split(" ")[0].equalsIgnoreCase("Bearer")){
            return null;
        }
        return header.split(" ")[1];
    }
}
