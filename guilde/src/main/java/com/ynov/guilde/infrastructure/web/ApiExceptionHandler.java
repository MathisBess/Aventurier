package com.ynov.guilde.infrastructure.web;

import com.ynov.guilde.domain.exception.AventurierNonTrouveException;
import com.ynov.guilde.domain.exception.NomInvalideException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.net.URI;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(NomInvalideException.class)
    public ProblemDetail handleNomInvalideException(NomInvalideException ex) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
        problem.setTitle("Règle métier non respectée");
        problem.setType(URI.create("about:blank"));
        return problem;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidationExceptions(MethodArgumentNotValidException ex) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "La requête contient des données invalides.");
        problem.setTitle("Erreur de Validation");
        problem.setType(URI.create("about:blank"));
        
        problem.setProperty("erreurs", ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + " : " + error.getDefaultMessage())
                .toList());
                
        return problem;
    }

    @ExceptionHandler(AventurierNonTrouveException.class)
    public ProblemDetail handleAventurierNonTrouveException(AventurierNonTrouveException ex) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        problem.setTitle("Ressource introuvable");
        problem.setType(URI.create("about:blank"));
        return problem;
    }
}