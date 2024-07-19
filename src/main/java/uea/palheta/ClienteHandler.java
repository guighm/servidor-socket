package uea.palheta;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.SocketException;

public class ClienteHandler implements Runnable{

    private Socket connection;

    public ClienteHandler(Socket connection) {
        this.connection = connection;
    }

    @Override
    public void run() {
        try {
            ObjectInputStream entrada = new ObjectInputStream(connection.getInputStream());
            PrintWriter out = new PrintWriter(connection.getOutputStream(), true);
            out.println("Olá, Cliente");
            String mensagem;
        do {
            mensagem = (String) entrada.readObject();

            String[] partes = mensagem.split(":");

            int comando = Integer.parseInt(partes[0]);

            switch (comando){
                case 1:
                //cadastrar professor
                    break;
                case 2:
                //cadastrar aluno
                    break;
                case 3:
                //cadastrar técnico
                    break;
                case 4:
                //login professor
                    break;
                case 5:
                //login aluno
                    break;
                case 6:
                //login técnico
                    break;
                case 7:
                //exibir status dos users
                    break;
                case 8:
                //enviar mensagem
                    break;
                default:
                    break;
            }
        } while (!mensagem.contains("TERMINATE"));

        entrada.close();
        connection.close();
        System.out.println("SERVIDOR ENCERRADO");

        } catch (SocketException e) {
            System.out.println("NINGUÉM ESTÁ ONLINE!");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }  
}