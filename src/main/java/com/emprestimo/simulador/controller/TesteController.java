package com.emprestimo.simulador.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class TesteController {

    @GetMapping()
    public String retornar(){
        return "Ok";
    }
}
