package uea.palheta;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.ArrayList;

public class ServidorSocket {
    public static List<Socket> clienteSockets = new ArrayList<>();
    public static void main(String[] args) throws IOException {

        ServerSocket server = new ServerSocket(12345);

        while (true) {
            Socket connection = server.accept();
            synchronized (clienteSockets) {
                clienteSockets.add(connection);
            }
            new Thread(new ClienteHandler(connection)).start();
        }
    }

    public static void listarSocketsConectados() {
        synchronized (clienteSockets) {
            System.out.println("Sockets conectados: ");
            for (Socket socket : clienteSockets) {
                if (!socket.isClosed()) {
                    System.out.println(socket.getInetAddress().getHostAddress() + ":" + socket.getPort());
                }
            }
        }
    }
}