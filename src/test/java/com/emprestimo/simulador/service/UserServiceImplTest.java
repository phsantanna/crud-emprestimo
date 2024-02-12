package com.emprestimo.simulador.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.emprestimo.simulador.dto.UsersDto;
import com.emprestimo.simulador.repository.UsersRepository;
import com.emprestimo.simulador.model.Users;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;


@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UsersRepository usersRepository;

    @InjectMocks
    private UserServiceImpl userService;

    UsersDto usersDto;
    Users users;

    @BeforeEach
    public void setup() {
        usersDto = new UsersDto("Paulo", "123", 601);
        users = new Users(usersDto.nome(), usersDto.cpf(), usersDto.score());
    }

    @Test
    void cadastrarUsuarioSuccess() {

        userService.cadastrarUsuario(usersDto);
        verify(usersRepository, times(1)).save(any());
    }


    @Test
    void cadastrarUsuarioFailure() {

        Users user = new Users("Paulo", "123", 600);

        when(usersRepository.save(user)).thenThrow(DataIntegrityViolationException.class);

        assertThrows(DataIntegrityViolationException.class, () -> {
            usersRepository.save(user);
        });

        verify(usersRepository).save(user);
    }
}
