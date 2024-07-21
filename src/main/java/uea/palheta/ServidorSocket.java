package uea.palheta;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class ServidorSocket {
    public static List<PrintWriter> clientes = Collections.synchronizedList(new ArrayList<>());
    public static void main(String[] args) throws IOException {

        ServerSocket server = new ServerSocket(12345);

        while (true) {
            Socket connection = server.accept();
            ClienteHandler clienteHandler = new ClienteHandler(connection);
            PrintWriter saida = new PrintWriter(connection.getOutputStream(), true);
            clientes.add(saida);
            clienteHandler.start();
        }
    }

    public static void broadcastMessage(String message) {

        PrintWriter saida;

        for (PrintWriter cliente : clientes) {
            saida = (PrintWriter) cliente;
            cliente.println(message);
            cliente.flush();
        }
    }

    // public static void closeConnection(int index) {
    //     synchronized (clientes) {
    //         if (index >= 0 && index < clientes.size()) {
    //             ClienteHandler clienteHandler = clientes.get(index);
    //             clienteHandler.closeConnection();
    //         } else {
    //             System.out.println("Índice inválido!");
    //         }
    //     }
    // }

    public static void listConnectedClientes() {
        synchronized (clientes) {
            System.out.println("Clientes conectados: ");
            for (int i = 0; i < clientes.size(); i++) {
                PrintWriter clienteHandler = clientes.get(i);
            }
        }
    }

    public static void removeClienteHandler(ClienteHandler clienteHandler) {
        synchronized (clientes) {
            clientes.remove(clienteHandler);
        }
    }
}