package com.example.desafio_itau_3.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.desafio_itau_3.dto.TransactionRequest;
import com.example.desafio_itau_3.model.Transaction;
import com.example.desafio_itau_3.service.TransactionService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestMapping;

import java.time.OffsetDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;



@RestController
@RequestMapping("/transacao")
@Tag(name = "Transações", description = "Operações relacionadas a transações")
public class TransactionController {

    private static final Logger log = 
            LoggerFactory.getLogger(TransactionController.class);

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService){
        this.transactionService = transactionService;
    }

    @Operation(summary = "Criar uma transação",description = "Registra uma nova transação válida")
    @ApiResponse(responseCode = "201", description = "Transação criada com sucesso")
    @ApiResponse(responseCode = "422", description = "Transação inválida")
    @ApiResponse(responseCode = "422", description = "Erro de regra de negócio", content = @Content(mediaType = "application/problem+json"))
    @PostMapping
    public ResponseEntity<Void> createTransaction(@Valid @RequestBody TransactionRequest request) {

        log.info("Recebida transação: valor={}, dataHora={}", request.getValor(), request.getDataHora());

        if (request.getDataHora().isAfter(OffsetDateTime.now()) || request.getValor() <= 0){
            log.warn("Transação inválida rejeitada: valor={}, dataHora={}", request.getValor(), request.getDataHora());
            throw new IllegalArgumentException("Invalid transaction");
        }

        transactionService.addTransaction(new Transaction(request.getValor(), request.getDataHora()));
        log.info("Transação registrada com sucesso");
        return ResponseEntity.status(HttpStatus.CREATED).build();  
    }

    @Operation(summary = "Remover todas as transações",description = "Remove todas as transações registradas")
    @ApiResponse(responseCode = "200", description = "Transações removidas")
    @DeleteMapping
    public ResponseEntity<Void> clearTransactions(){
        log.info("Requisição para limpar todas as transações");
        transactionService.clearTransactions();
        log.info("Transações removidas com sucesso");
        return ResponseEntity.ok().build();
    }
    

}