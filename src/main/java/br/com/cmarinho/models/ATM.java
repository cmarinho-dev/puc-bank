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
        StringBuilder str = new StringBuilder("-~-~-~-~-~-~-~-~-~-~-~-~-~-" +
                "\n* ATM STATUS\n" +
                "-~-~-~-~-~-~-~-~-~-~-~-~-~-\n");

        for (CashStock stock : stocks.values()) {
            str.append("""
                    %s:
                        status: %s
                        quant: %d
                    -~-~-~-~-~-~-~-~-~-~-~-~-~-
                    """
                    .formatted(
                            stock.getTypeOfCash().toText(),
                            stock.status(),
                            stock.getCashStock().size()
                    ));
        }
        System.out.println(str);
    }
}
