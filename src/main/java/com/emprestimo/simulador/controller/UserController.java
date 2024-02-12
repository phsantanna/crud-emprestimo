package com.emprestimo.simulador.controller;

import com.emprestimo.simulador.dto.UsersDto;
import com.emprestimo.simulador.service.UserService;
import com.emprestimo.simulador.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/painel")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/emprestimo")
    public ResponseEntity<Users> gerarEmprestimo(@RequestBody UsersDto usersDto){
        return ResponseEntity.ok(userService.cadastrarUsuario(usersDto));
    }

}
