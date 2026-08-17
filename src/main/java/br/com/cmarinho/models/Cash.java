package br.com.cmarinho.models;

import lombok.Data;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
public class Cash {
    private String identifier;
    private OffsetDateTime created_at;
    private CashEnum cashEnum;

    public Cash(CashEnum cashEnum) {
        this.identifier = UUID.randomUUID().toString();
        this.created_at = OffsetDateTime.now();
        this.cashEnum = cashEnum;
    }
}
