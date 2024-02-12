package com.emprestimo.simulador.controller;



import com.emprestimo.simulador.dto.UsersAccessDto;
import com.emprestimo.simulador.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> auth(@RequestBody UsersAccessDto usersAccessDto){

        var authenticationToken = new UsernamePasswordAuthenticationToken(usersAccessDto.login(),usersAccessDto.senha());
        authenticationManager.authenticate(authenticationToken);
        String token = authenticationService.obterToken(usersAccessDto.login());
        return ResponseEntity.ok(token);
    }

}
