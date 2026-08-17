package br.com.cmarinho;

import br.com.cmarinho.models.ATM;
import br.com.cmarinho.models.CashStock;
import br.com.cmarinho.models.Client;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

import static br.com.cmarinho.models.CashEnum.*;

public class Main {
    private static final int MAX_SIZE = 5;

    public static void main(String[] args) {
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

        Queue<Client> supportQueue = new ArrayBlockingQueue<>(MAX_SIZE);
        supportQueue.add(charles);
        supportQueue.add(peter);

        System.out.println("Fila de Atendimento");
        System.out.println("1º: " + supportQueue.poll().getName());
        System.out.println("2º: " + supportQueue.poll().getName());
        System.out.println("3º: " + supportQueue.poll());

        simulateATM();
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

        System.out.println("Sacando 100 reais : ");
        machine.printStatus();
        System.out.println(machine.stocks().get(one_hundred.value()).getCashStock().pop());
        machine.printStatus();

        System.out.println("Sacando 50 reais : ");
        machine.printStatus();
        System.out.println(machine.stocks().get(fifty.value()).getCashStock().pop());
        machine.printStatus();
    }
}