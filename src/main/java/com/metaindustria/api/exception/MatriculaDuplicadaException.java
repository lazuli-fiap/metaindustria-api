package com.metaindustria.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class MatriculaDuplicadaException extends RuntimeException {
    public MatriculaDuplicadaException(String matricula) {
        super("Ja existe um funcionario cadastrado com a matricula " + matricula);
    }
}
