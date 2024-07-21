package uea.palheta;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

public class Application {

    public static ConcurrentHashMap<Integer, Socket> connections = new ConcurrentHashMap<>();
    public static ConcurrentHashMap<Integer, String> apelidos = new ConcurrentHashMap<>();

    private static int i;
    public static void main(String[] args) throws IOException {

        ServerSocket server = new ServerSocket(12345);
        
        i = 1;

        while (true) {
            Socket connection = server.accept();
            connections.put(i, connection);
            String ip = connection.getInetAddress().toString().replace("/", "");
            String port = Integer.toString(connection.getPort());
            apelidos.put(i, "%s - %s".formatted(ip, port));
            i++;
            System.out.println(connections);
            System.out.println(apelidos);
            new Thread(new ClienteHandler(connection)).start();
        }
    }
}