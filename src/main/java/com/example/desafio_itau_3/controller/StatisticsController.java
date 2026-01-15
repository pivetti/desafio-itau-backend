package com.example.desafio_itau_3.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.desafio_itau_3.dto.StatisticsResponse;
import com.example.desafio_itau_3.service.TransactionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.util.DoubleSummaryStatistics;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/estatistica")
@Tag(name = "Estatísticas", description = "Consulta de estatísticas")
public class StatisticsController {
    
    private static final Logger log =
        LoggerFactory.getLogger(StatisticsController.class);
    
    private final TransactionService transactionService;

    public StatisticsController(TransactionService transactionService){
        this.transactionService = transactionService;
    }

    @Operation(
        summary = "Obter estatísticas",
        description = "Retorna estatísticas das transações dos últimos 60 segundos"
    )
    @ApiResponse(responseCode = "200", description = "Estatísticas retornadas")
    @GetMapping
    public ResponseEntity<StatisticsResponse> getStatistics() {
        log.info("Requisição de estatísticas");
        DoubleSummaryStatistics stats = transactionService.getStatistics();
        return ResponseEntity.ok(new StatisticsResponse(stats));
    }
}
