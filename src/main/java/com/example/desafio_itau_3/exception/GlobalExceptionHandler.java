package com.example.desafio_itau_3.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log =
            LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // 1️⃣ Erro de validação (400)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    ProblemDetail handleValidationError(
            MethodArgumentNotValidException ex,
            HttpServletRequest request) {

        log.warn("Validation error", ex);

        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problem.setTitle("Validation error");
        problem.setDetail("One or more fields are invalid");
        problem.setInstance(java.net.URI.create(request.getRequestURI()));

        return problem;
    }

    // 2️⃣ Regra de negócio (422)
    @ExceptionHandler(IllegalArgumentException.class)
    ProblemDetail handleBusinessError(
            IllegalArgumentException ex,
            HttpServletRequest request) {

        log.warn("Business rule violation: {}", ex.getMessage());

        ProblemDetail problem =
                ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);

        problem.setTitle("Business rule violation");
        problem.setDetail(ex.getMessage());
        problem.setType(
            java.net.URI.create("https://example.com/problems/business-rule")
        );
        problem.setInstance(java.net.URI.create(request.getRequestURI()));

        return problem;
    }

    // 3️⃣ Erro inesperado (500)
    @ExceptionHandler(Exception.class)
    ProblemDetail handleGenericError(
            Exception ex,
            HttpServletRequest request) {

        log.error("Unexpected error", ex);

        ProblemDetail problem =
                ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);

        problem.setTitle("Internal server error");
        problem.setDetail("Unexpected error occurred");
        problem.setInstance(java.net.URI.create(request.getRequestURI()));

        return problem;
    }
}
