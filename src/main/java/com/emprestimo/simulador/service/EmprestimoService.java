package com.emprestimo.simulador.service;

import com.emprestimo.simulador.model.Users;
import org.springframework.stereotype.Service;


@Service
public interface EmprestimoService {
    public String verificarSituacao(Users users);
}
