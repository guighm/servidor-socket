package uea.palheta;

import java.net.ServerSocket;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import uea.palheta.view.StartFrame;

public class Application {
    private static int port = 12345;
    private static ServerSocket server = null;
    public static void main(String[] args) {

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
    }
}