package com.emprestimo.simulador.service;


import com.emprestimo.simulador.dto.UsersAccessDto;
import com.emprestimo.simulador.repository.UserAccesPlatformRepository;
import com.emprestimo.simulador.model.UsersAccessPlatform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserAccessPlatformService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserAccesPlatformRepository userAccesPlatformRepository;

    public UsersAccessDto cadastrarAdmin(UsersAccessDto admin){
        var passwordHash = passwordEncoder.encode(admin.senha());
        UsersAccessPlatform user = new UsersAccessPlatform(admin.login(),passwordHash,admin.userRole());
        UsersAccessPlatform newUser = userAccesPlatformRepository.save(user);
        return new UsersAccessDto(newUser.getLogin(), newUser.getPassword(),newUser.getUserRole());
    }

}
