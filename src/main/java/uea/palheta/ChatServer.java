package uea.palheta;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

public class ChatServer {
    public static List<Handler> conexoes = new ArrayList<>(); 
    public static Set<Socket> clientes = new HashSet<>();
    public static void main(String[] args) throws IOException {

        ServerSocket server = new ServerSocket(12345);
        System.out.println("O SERVIDOR DO WHATSAPP 2.0 FOI INICIADO NA PORTA 12345");

        while (true) {
            Socket connection = server.accept();
            clientes.add(connection);
            Handler clienteHandler = new Handler(connection);
            conexoes.add(clienteHandler);
            Thread thread = new Thread(clienteHandler);
            thread.start();
        }
    }

    public static void sendToAll(String mensagem) {
        for (Socket socket : clientes) {
            try {
                PrintWriter saida = new PrintWriter(socket.getOutputStream(), true);
                saida.println(mensagem);
            } catch (IOException e) {
                e.printStackTrace();
            }
            }
        }

    public static void removeClienteHandler(Handler clienteHandler) {
        conexoes.remove(clienteHandler);
    }

    public static void closeConnection(int index) {
        Handler clienteHandler = conexoes.get(index);
        clienteHandler.closeConnection();
    }

    public static String listarConexoes() {
        String texto = "Clientes conectados";
        for (int i = 0; i < conexoes.size(); i++) {
            Handler clienteHandler = conexoes.get(i);
            texto += ":[%d] %s".formatted(i, clienteHandler.getClientInfo());
        }
        return texto;
    }
}