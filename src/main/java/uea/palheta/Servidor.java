package uea.palheta;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Servidor {
    public static List<ClienteHandler> conexoes = new ArrayList<>(); 
    public static void main(String[] args) throws IOException {

        ServerSocket server = new ServerSocket(12345);

        while (true) {
            Socket connection = server.accept();
            ClienteHandler clienteHandler = new ClienteHandler(connection);
            conexoes.add(clienteHandler);
            Thread thread = new Thread(clienteHandler);
            thread.start();
        }
    }

    public static void sendToAll(String mensagem, ClienteHandler sender) {
        for (ClienteHandler clienteHandler : conexoes) {
            if (clienteHandler != sender) {
                clienteHandler.sendMessage(mensagem);
            }
        }
    }

    public static void removeClienteHandler(ClienteHandler clienteHandler) {
        conexoes.remove(clienteHandler);
    }

    public static void closeConnection(int index) {
        ClienteHandler clienteHandler = conexoes.get(index);
        clienteHandler.closeConnection();
    }

    public static String listarConexoes() {
        String texto = "Clientes conectados";
        for (int i = 0; i < conexoes.size(); i++) {
            ClienteHandler clienteHandler = conexoes.get(i);
            texto += ":[%d] %s".formatted(i, clienteHandler.getClientInfo());
        }
        return texto;
    }
}