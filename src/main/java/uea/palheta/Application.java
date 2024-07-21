package uea.palheta;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Application {
    public static void main(String[] args) throws IOException {

        ServerSocket server = new ServerSocket(12345);

        while (true) {
            Socket connection = server.accept();
            new Thread(new ClienteHandler(connection)).start();
        }
    }
}