package com.emprestimo.simulador.service;

import com.emprestimo.simulador.dto.UsersDto;
import com.emprestimo.simulador.model.Users;

import java.util.List;

public interface UserService {
    public Users cadastrarUsuario(UsersDto usersDto);
    public List<Users> listarTodos();
    public void removerUsuario();
    Users buscarUsuario(String nome);
}
