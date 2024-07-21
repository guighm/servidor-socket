package uea.palheta;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.SocketException;
import uea.palheta.model.aluno.Aluno;
import uea.palheta.model.aluno.AlunoDAO;
import uea.palheta.model.mensagem.MensagemDAO;
import uea.palheta.model.professor.Professor;
import uea.palheta.model.professor.ProfessorDAO;
import uea.palheta.model.tecnico.Tecnico;
import uea.palheta.model.tecnico.TecnicoDAO;

public class ClienteHandler extends Thread {

    private Socket connection;
    private PrintWriter saida;

    public ClienteHandler(Socket connection) {
        this.connection = connection;
    }

    public void run() {
        try {
            ObjectInputStream entrada = new ObjectInputStream(connection.getInputStream());
            this.saida = new PrintWriter(connection.getOutputStream(), true);
            String mensagem;
            while (true) {
                mensagem = (String) entrada.readObject();
                String response = processarComando(mensagem);
                saida.println(response);

                if (mensagem.contains("SAIR")) break;
            }

        
    } catch (SocketException e) {
        System.out.println("NINGUÉM ESTÁ ONLINE!");
    } catch (IOException | ClassNotFoundException e) {
        e.printStackTrace();
    } finally {
        closeConnection();
    }
    }  

    private String processarComando(String request) throws IOException {
        String[] partes = request.split(":");

        int comando;
        try {
            comando = Integer.parseInt(partes[0]);
        } catch (NumberFormatException e) {
            return "Comando Inválido!";
        }


        String response = "";
        String login = partes[1];
        String senha = partes[2];

        switch (comando){
            case 1:
                int anoDeIngresso = Integer.parseInt(partes[3]);
                Aluno aluno1 = new Aluno(login, senha, anoDeIngresso);
                response = AlunoDAO.cadastrarAluno(aluno1);
                return response;
            case 2:
                String titulacao = partes[3];
                Professor professor1 = new Professor(login, senha, titulacao);
                response = ProfessorDAO.cadastrarProfessor(professor1); 
                return response;
            case 3:
                Tecnico tecnico1 = new Tecnico(login, senha);
                response = TecnicoDAO.cadastrarTecnico(tecnico1);
                return response;
            case 4:
                response = AlunoDAO.logarAluno(login, senha);
                return response;
            case 5:
                response = ProfessorDAO.logarProfessor(login, senha);
                return response;
            case 6:
                response = TecnicoDAO.logarTecnico(login, senha);
                return response;
            case 7:
                response = AlunoDAO.listarAlunos();
                return response;
            case 8:
                response = ProfessorDAO.listarProfessores();
                return response;
            case 9:
                response = TecnicoDAO.listarTecnicos();
                return response;
            case 10:
                response = MensagemDAO.buscarMensagens();
                return response;
            case 11: // mensagem
                MensagemDAO.cadastrarMensagem(login); 
                return null;
            case 12: // status
                System.out.println(login);
                MensagemDAO.cadastrarMensagem(login); 
                return null;
            case 13:
                return null;
            case 14:
                return null;
            default:
                return null;
    }
}

    public void sendMessage(String message) {
        if (this.saida != null) {
            this.saida.println(message);
        }
    }

    public void closeConnection() {
        try {
            connection.close();
            ServidorSocket.removeClienteHandler(this);
            System.out.println("Cliente desconectado: " + connection.getInetAddress().getHostAddress());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getClienteInfo() {
        return connection.getInetAddress().getHostAddress() + ":" + connection.getPort();
    }
}