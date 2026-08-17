package br.com.cmarinho.models;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class Client {
    private String name;
    private String cpf;
    private LocalDate birthDate;
    private final List<Account> accounts;

    public Client(String name, String cpf, LocalDate birthDate) {
        this(name, cpf, birthDate, new ArrayList<>());
    }

    public Client(String name, String cpf, LocalDate birthDate, List<Account> accounts) {
        this.name = name;
        this.cpf = cpf;
        this.birthDate = birthDate;
        this.accounts = accounts == null ?
                new ArrayList<>() : new ArrayList<>(accounts);
    }

    public Client(String name, String cpf, LocalDate birthDate, Account account) {
        this(name, cpf, birthDate);

        if (account != null)
            this.accounts.add(account);
    }
}
