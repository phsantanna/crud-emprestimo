package com.emprestimo.simulador.repository;

import com.emprestimo.simulador.model.UsersAccessPlatform;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserAccesPlatformRepository extends JpaRepository<UsersAccessPlatform, Long> {
    public UsersAccessPlatform findByLogin(String login);
}
