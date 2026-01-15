package com.example.desafio_itau_3.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.OffsetDateTime;

import org.junit.jupiter.api.Test;

import com.example.desafio_itau_3.model.Transaction;

class TransactionServiceTest {

    private TransactionService service;

    @Test
    void shouldReturnZeroStatisticsWhenNoTransactions() {
        var stats = service.getStatistics();

        assertThat(stats.getCount()).isZero();
        assertThat(stats.getSum()).isZero();
        assertThat(stats.getAverage()).isZero();
    }

    @Test
    void shouldIncludeTransactionWithinLast60Seconds() {
        service.addTransaction(
            new Transaction(10.0, OffsetDateTime.now().minusSeconds(10))
        );

        var stats = service.getStatistics();

        assertThat(stats.getCount()).isEqualTo(1);
        assertThat(stats.getSum()).isEqualTo(10.0);
    }

    @Test
    void shouldIgnoreTransactionOlderThan60Seconds(){
        service.addTransaction(
            new Transaction(10.0, OffsetDateTime.now().minusSeconds(61))
        );

        var stats = service.getStatistics();

        assertThat(stats.getCount()).isZero();  
    }

    @Test
    void shouldCalculateStatisticsOnlyWithValidTransactions() {
        service.addTransaction(
            new Transaction(10.0, OffsetDateTime.now().minusSeconds(10))
        );

        service.addTransaction(
            new Transaction(20.0, OffsetDateTime.now().minusSeconds(70))
        );

        var stats = service.getStatistics();

        assertThat(stats.getCount()).isEqualTo(1);  
        assertThat(stats.getSum()).isEqualTo(10.0);
    }
}
