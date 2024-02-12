package com.emprestimo.simulador.repository;

import com.emprestimo.simulador.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

        Users findByNome(String nome);
    }