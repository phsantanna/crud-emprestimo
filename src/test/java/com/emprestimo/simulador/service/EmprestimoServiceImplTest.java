package com.emprestimo.simulador.service;

import com.emprestimo.simulador.enums.Situacao;
import com.emprestimo.simulador.repository.EmprestimoRepository;
import com.emprestimo.simulador.model.Users;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmprestimoServiceImplTest {

    @Mock
    private EmprestimoRepository emprestimoRepository;

    @InjectMocks
    private EmprestimoServiceImpl emprestimoService;

    Users users;

    @BeforeEach
    public void setup() {
        users = mock(Users.class);

    }

    @Test
    void verificarSituacaoAprovado() {
        when(users.getScore()).thenReturn(601);

        String resultado = emprestimoService.verificarSituacao(users);

        assertEquals("Emprestimo aprovado", resultado);

        verify(users).setSituacao(Situacao.APROVADO);
        verify(emprestimoRepository,times(1)).save(any());
    }

    @Test
    void verificarSituacaoNegado() {
        when(users.getScore()).thenReturn(600);

        String resultado = emprestimoService.verificarSituacao(users);

        assertEquals("Emprestimo negado", resultado);

        verify(users).setSituacao(Situacao.NEGADO);
        verify(emprestimoRepository,never()).save(any());
    }

}