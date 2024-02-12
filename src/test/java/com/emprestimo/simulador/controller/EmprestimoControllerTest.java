package com.emprestimo.simulador.controller;

import com.emprestimo.simulador.service.EmprestimoServiceImpl;
import com.emprestimo.simulador.service.UserServiceImpl;
import com.emprestimo.simulador.model.Users;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class EmprestimoControllerTest {

    @MockBean
    private EmprestimoServiceImpl emprestimoService;
    @MockBean
    private UserServiceImpl userService;
    @Autowired
    private MockMvc mockMvc;


    @Test
    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    void addEmprestimo() throws Exception {
        String usuarioDesejado = "Paulo";

        Users newUser = new Users("Paulo","123",700);

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("nome", usuarioDesejado);

        when(userService.buscarUsuario(usuarioDesejado)).thenReturn(newUser);

        when(emprestimoService.verificarSituacao(newUser)).thenReturn("Emprestimo aprovado");
        mockMvc.perform(post("/emprestimo/solicitar-emprestimo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\": \"Paulo\"}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(userService,times(1)).buscarUsuario(usuarioDesejado);
        verify(emprestimoService, times(1)).verificarSituacao(newUser);

    }
}