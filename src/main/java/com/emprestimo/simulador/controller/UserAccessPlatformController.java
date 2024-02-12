package com.emprestimo.simulador.controller;


import com.emprestimo.simulador.dto.UsersAccessDto;
import com.emprestimo.simulador.service.UserAccessPlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserAccessPlatformController {

    @Autowired
    private UserAccessPlatformService userAccessPlatformService;


    @PostMapping("/cadastrar")
    public ResponseEntity<UsersAccessDto> cadastrarAdmin(@RequestBody UsersAccessDto usersAccessDto) {
        userAccessPlatformService.cadastrarAdmin(usersAccessDto);
        return new ResponseEntity<>(usersAccessDto, HttpStatus.CREATED);
    }
}

