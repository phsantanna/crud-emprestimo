package com.emprestimo.simulador.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.emprestimo.simulador.dto.UsersDto;
import com.emprestimo.simulador.service.UserServiceImpl;
import com.emprestimo.simulador.model.Users;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

@AutoConfigureMockMvc
@SpringBootTest
public class UserControllerTest {

    @MockBean
    private UserServiceImpl usersService;

    @Autowired
    private MockMvc mockMvc;


    @Test
    @WithMockUser(username="user", password="123", roles="ADMIN")
    public void testCadastrarUsuario() throws Exception {
        UsersDto userDto = new UsersDto("John Doe", "123456789", 750);

        Users newUser = new Users("John Doe", "123456789", 750);
        when(usersService.cadastrarUsuario(userDto)).thenReturn(newUser);

        mockMvc.perform(post("/painel/emprestimo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\": \"Paulo\", \"cpf\": \"123456789\", \"score\": 750}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value(newUser.getNome()))
                .andExpect(jsonPath("$.cpf").value(newUser.getCpf()))
                .andExpect(jsonPath("$.score").value(newUser.getScore()));

        verify(usersService, times(1)).cadastrarUsuario(userDto);
    }

}
