package br.com.cmarinho.models;

import java.util.ArrayDeque;
import java.util.Queue;

public class ClientSupportQueue {
    private final Queue<Client> clients;

    public ClientSupportQueue(int maxSize) {
        this.clients = new ArrayDeque<>(maxSize);
    }

    public boolean addClient(Client client) {
        if (client == null) {
            System.out.println("Cliente nulo não pode ser adicionado.");
            return false;
        }

        if (clients.size() >= getCapacity()) {
            System.out.println("Fila cheia. Cliente não adicionado: " + client.getName());
            return false;
        }

        clients.offer(client);
        System.out.println("Cliente adicionado: " + client.getName() + " | fila: " + clients.size());
        return true;
    }

    public Client attendNextClient() {
        if (clients.isEmpty()) {
            System.out.println("Fila vazia. Nenhum cliente para atender.");
            return null;
        }

        Client attended = clients.poll();
        System.out.println("Atendendo cliente: " + attended.getName());
        return attended;
    }

    public boolean isEmpty() {
        return clients.isEmpty();
    }

    public int size() {
        return clients.size();
    }

    public int getCapacity() {
        return 5;
    }

    public Queue<Client> getClients() {
        return clients;
    }

    public void printStatus(String title) {
        System.out.println("-~-~-~-~-~-~-~-~-~-~-~-~-~-");
        System.out.println("* " + title);
        System.out.println("tamanho: " + clients.size());

        if (clients.isEmpty()) {
            System.out.println("fila: vazia");
        } else {
            StringBuilder queueStatus = new StringBuilder("fila: ");
            for (Client client : clients) {
                queueStatus.append(client.getName()).append(" | ");
            }
            System.out.println(queueStatus);
        }

        System.out.println("-~-~-~-~-~-~-~-~-~-~-~-~-~-\n");
    }
}
