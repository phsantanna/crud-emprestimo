package com.emprestimo.simulador.enums;


import lombok.Getter;
import lombok.Setter;

@Getter
public enum UserRole {
    ADMIN("admin"), USER("user");

    private final String descricao;

    UserRole(String descricao) {
        this.descricao = descricao;
    }
}
