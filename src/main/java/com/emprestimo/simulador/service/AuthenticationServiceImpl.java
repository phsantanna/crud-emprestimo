package com.emprestimo.simulador.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.emprestimo.simulador.repository.UserAccesPlatformRepository;
import com.emprestimo.simulador.model.UsersAccessPlatform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;


@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private UserAccesPlatformRepository userAccesPlatformRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userAccesPlatformRepository.findByLogin(username);
    }

    @Override
    public String obterToken(String username) {
        UsersAccessPlatform user = userAccesPlatformRepository.findByLogin(username);
        return generateToken(user);
    }

    public String generateToken(UsersAccessPlatform usersAccessPlatform){

        try {
            Algorithm algorithm = Algorithm.HMAC256("mysecret");
            return JWT.create()
                    .withIssuer("auth-api")
                    .withSubject(usersAccessPlatform.getLogin())
                    .withExpiresAt(generateExpirationDate())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException("Erro ao tentar gerar token"+ exception.getMessage());
        }
    }

    private Instant generateExpirationDate() {
        return LocalDateTime.now().plusHours(8).toInstant(ZoneOffset.of("-03:00"));
    }

    public String validateToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256("mysecret");
            return JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .build()
                    .verify(token)
                    .getSubject();

        } catch (JWTVerificationException exception){
            return " ";
        }
    }
}
