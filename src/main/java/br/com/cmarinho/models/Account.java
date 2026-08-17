package br.com.cmarinho.models;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Account {
    private String agency = "007";
    private String number;
    private BigDecimal balance;
    private boolean isActive;
}
