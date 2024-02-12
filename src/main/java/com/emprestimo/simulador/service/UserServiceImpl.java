package com.emprestimo.simulador.service;


import com.emprestimo.simulador.dto.UsersDto;
import com.emprestimo.simulador.enums.Situacao;
import com.emprestimo.simulador.infra.exceptions.EmprestimoException;
import com.emprestimo.simulador.repository.UsersRepository;
import com.emprestimo.simulador.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersRepository usersRepository;



    public Users cadastrarUsuario(UsersDto usersDto) {
        Users userExists = usersRepository.findByNome(usersDto.nome());
        if (userExists == null) {
            Users users = new Users(usersDto.nome(), usersDto.cpf(), usersDto.score());
            users.setSituacao(Situacao.VERIFICANDO);
            return usersRepository.save(users);
        }
        throw new EmprestimoException("Usuário já existe");
    }

    public List<Users> listarTodos() {
        return usersRepository.findAll();
    }

    public void removerUsuario() {
        usersRepository.deleteAll();
    }

    public Users buscarUsuario(String nome) {

        return usersRepository.findByNome(nome);
    }
}
