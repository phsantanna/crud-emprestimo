package com.emprestimo.simulador.service;


import com.emprestimo.simulador.dto.AuthDto;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public interface AuthenticationService extends UserDetailsService {

    public String obterToken(String username);
    public String validateToken(String token);
}
