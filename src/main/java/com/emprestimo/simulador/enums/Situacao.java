package com.emprestimo.simulador.enums;

public enum Situacao {
    APROVADO("aprovado"),
    NEGADO("negado"),
    VERIFICANDO("verificando");

    private final String descricao;

    Situacao(String descricao) {
        this.descricao = descricao;
    }
}
