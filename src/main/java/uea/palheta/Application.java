package uea.palheta;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import uea.palheta.view.StartFrame;

public class Application {
    public static void main(String[] args) throws IOException {

        ServerSocket server = new ServerSocket(12345);

        String aviso = """
                   ATENÇÃO! VOCÊ ESTÁ ENTRANDO EM UM 
                  SERVIDOR SOCKET! ESSA ÁREA É RESERVADA 
                APENAS A TÉCNICOS PREVIAMENTE AUTORIZADOS!
                """;

        JOptionPane.showMessageDialog(null, aviso, "ATENÇÃO!", JOptionPane.WARNING_MESSAGE);

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new StartFrame().setVisible(true);
            }
        });

        while (true) {
            Socket connection = server.accept();
            new Thread(new ClienteHandler(connection)).start();
        }
    }
}