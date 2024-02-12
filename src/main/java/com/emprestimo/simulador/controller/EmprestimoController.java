package com.emprestimo.simulador.controller;

import com.emprestimo.simulador.model.Emprestimo;
import com.emprestimo.simulador.service.EmprestimoServiceImpl;
import com.emprestimo.simulador.service.UserServiceImpl;
import com.emprestimo.simulador.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
@RequestMapping("/emprestimo")
public class EmprestimoController {

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private EmprestimoServiceImpl emprestimoService;

    @PostMapping("/solicitar-emprestimo")
    public ResponseEntity<Emprestimo> addEmprestimo(@RequestBody Map<String, String> requestBody) {
        String nome = requestBody.get("nome");
        Users newUser = userService.buscarUsuario(nome);
        emprestimoService.verificarSituacao(newUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
