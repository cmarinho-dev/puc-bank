package br.com.cmarinho.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Stack;

@Data
@AllArgsConstructor
public class CashStock {
    private final CashEnum typeOfCash;
    private final Stack<Cash> cashStock;

    public CashStock(CashEnum cashEnum) {
        this.cashStock = new Stack<>();
        this.typeOfCash = cashEnum;
    }

    public static CashStock fromNewCashes(int quant, CashEnum cashEnum) {
        var tmpStock = new CashStock(cashEnum);
        for (int i = 0; i < quant; i++)
            tmpStock.cashStock.push(new Cash(cashEnum));
        return tmpStock;
    }

    public String status() {
        return cashStock.empty() ?
                "empty" : "occupied";
    }
}
