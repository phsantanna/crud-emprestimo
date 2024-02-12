package com.emprestimo.simulador.dto;

import com.emprestimo.simulador.enums.UserRole;

public record UsersAccessDto(String login, String senha, UserRole userRole) {
}
