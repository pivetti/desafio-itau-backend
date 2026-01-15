package com.example.desafio_itau_3.model;

import java.time.OffsetDateTime;

public class Transaction {
    private Double valor;
    private OffsetDateTime dataHora;

    public Transaction(final Double valor, final OffsetDateTime dataHora){
        this.valor = valor;
        this.dataHora = dataHora;
    }

    public Double getValor(){
        return valor;
    }

    public OffsetDateTime getDataHora(){
        return dataHora;
    }
}
