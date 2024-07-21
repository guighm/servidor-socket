package uea.palheta;

import java.util.ArrayList;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor extends Thread {
    private static ArrayList<PrintWriter> clientes;
    private static ServerSocket server;
    private String user;
    private Socket connection;
    

}
