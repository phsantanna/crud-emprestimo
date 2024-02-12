package com.emprestimo.simulador.infra.exceptions;


import com.emprestimo.simulador.infra.exceptions.ApiError;
import com.emprestimo.simulador.infra.exceptions.EmprestimoException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppResourceAdvice {

    @ExceptionHandler(EmprestimoException.class)
    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    public ApiError handleEmprestimoException(EmprestimoException exception) {
        return new ApiError(exception.getMessage());
    }
}
