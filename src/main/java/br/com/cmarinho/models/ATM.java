package br.com.cmarinho.models;

import lombok.Data;

import java.util.HashMap;

import static br.com.cmarinho.models.CashEnum.one_hundred;

@Data
public class ATM {
    private final HashMap<Integer, CashStock> stocks;

    public ATM() {
        this.stocks = new HashMap<>();
    }

    public HashMap<Integer, CashStock> stocks() {
        return this.getStocks();
    }

    public void printStatus() {
        StringBuilder str = new StringBuilder("\n* ATM STATUS\n"
            + "-~-~-~-~-~-~-\n");

        StringBuilder values = new StringBuilder();
        StringBuilder statuses = new StringBuilder();
        StringBuilder quantities = new StringBuilder();

        for (CashStock stock : stocks.values()) {
            values.append("%-20s | ".formatted("$%d".formatted(stock.getTypeOfCash().value())));
            statuses.append("%-20s | ".formatted("status=" + stock.status()));
            quantities.append("%-20s | ".formatted("quant=" + stock.getCashStock().size()));
        }

        str.append(values)
                .append("\n")
                .append(statuses)
                .append("\n")
                .append(quantities)
                .append("\n\n");
        System.out.println(str);
    }
}
