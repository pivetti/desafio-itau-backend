package com.example.desafio_itau_3.service;

import java.time.OffsetDateTime;
import java.util.DoubleSummaryStatistics;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.springframework.stereotype.Service;
import com.example.desafio_itau_3.model.Transaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class TransactionService {

    private static final Logger log = 
            LoggerFactory.getLogger(TransactionService.class);
    
    private final Queue<Transaction> transactions = new ConcurrentLinkedQueue<>();

    public void addTransaction(Transaction transaction){
        transactions.add(transaction);
        log.debug("Transação adicionada à fila");
    }
    
    public void clearTransactions(){
        transactions.clear();
        log.info("Fila de transações limpa");
    }

    public DoubleSummaryStatistics getStatistics(){
        long start = System.nanoTime();

        OffsetDateTime now = OffsetDateTime.now();

        DoubleSummaryStatistics stats = transactions.stream()
                .filter(t -> t.getDataHora().isAfter(now.minusSeconds(60)))
                .mapToDouble(Transaction::getValor)
                .summaryStatistics();

        long end = System.nanoTime();

        log.info("Cálculo das estatísticas levou {} µs (janela={}s)",
                (end - start) / 1_000,
                60);

        return stats;
    }

}
