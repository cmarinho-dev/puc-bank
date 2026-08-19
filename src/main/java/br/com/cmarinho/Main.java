package br.com.cmarinho;

import br.com.cmarinho.models.ATM;
import br.com.cmarinho.models.CashStock;
import br.com.cmarinho.models.Client;
import br.com.cmarinho.models.ClientSupportQueue;

import java.time.LocalDate;
import java.util.ArrayList;

import static br.com.cmarinho.models.CashEnum.*;

public class Main {
    public static void main(String[] args) {
        simulateClientQueue();
        simulateATM();
    }

    private static void simulateClientQueue() {
        ClientSupportQueue supportQueue = new ClientSupportQueue(5);

        Client charles = Client.builder()
                .name("Charles Stark")
                .cpf("12300032100")
                .birthDate(LocalDate.of(1989, 10, 29))
                .accounts(new ArrayList<>())
                .build();
        Client peter = Client.builder()
                .name("Peter Parker")
                .cpf("99900099900")
                .birthDate(LocalDate.of(2000, 2, 12))
                .accounts(new ArrayList<>())
                .build();
        Client wanda = Client.builder()
                .name("Wanda Maximoff")
                .cpf("33344455566")
                .birthDate(LocalDate.of(1992, 7, 18))
                .accounts(new ArrayList<>())
                .build();

        supportQueue.addClient(charles);
        supportQueue.addClient(peter);
        supportQueue.addClient(wanda);

        supportQueue.printStatus("Fila de Atendimento");

        System.out.println("Atendendo 1º cliente: " + supportQueue.attendNextClient().getName());
        supportQueue.printStatus("Fila após atendimento");

        System.out.println("Atendendo 2º cliente: " + supportQueue.attendNextClient().getName());
        supportQueue.printStatus("Fila após atendimento");
    }

    private static void simulateATM() {
        CashStock tenStock = CashStock.fromNewCashes(100, ten);
        CashStock twentyStock = CashStock.fromNewCashes(120, twenty);
        CashStock fiftyStock = CashStock.fromNewCashes(200, fifty);
        CashStock oneHundredStock = CashStock.fromNewCashes(300, one_hundred);

        ATM machine = new ATM();
        machine.stocks().put(ten.value(), tenStock);
        machine.stocks().put(twenty.value(), twentyStock);
        machine.stocks().put(fifty.value(), fiftyStock);
        machine.stocks().put(one_hundred.value(), oneHundredStock);

        System.out.println("Status da pilha do ATM antes da retirada:");
        machine.printStatus();

        System.out.println("Sacando 100 reais: ");
        System.out.println(machine.stocks().get(one_hundred.value()).getCashStock().pop());
        machine.printStatus();

        System.out.println("Sacando 50 reais: ");
        System.out.println(machine.stocks().get(fifty.value()).getCashStock().pop());
        machine.printStatus();
    }
}