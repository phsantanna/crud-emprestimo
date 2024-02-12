package com.emprestimo.simulador.repository;

import com.emprestimo.simulador.model.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {
}