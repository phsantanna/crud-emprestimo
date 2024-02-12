package com.emprestimo.simulador.service;

import com.emprestimo.simulador.model.Emprestimo;
import com.emprestimo.simulador.enums.Situacao;
import com.emprestimo.simulador.repository.EmprestimoRepository;
import com.emprestimo.simulador.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class EmprestimoServiceImpl implements EmprestimoService {
    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Override
    public String verificarSituacao(Users users) {
        if (users.getScore() <= 600) {
            users.setSituacao(Situacao.NEGADO);
            return "Emprestimo negado";
        }
        users.setSituacao(Situacao.APROVADO);
        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setUser(users);
        emprestimo.setTotal(1000F);
        emprestimo.setDataSolicitacao(LocalDate.now());
        emprestimoRepository.save(emprestimo);
        return "Emprestimo aprovado";

    }
}
