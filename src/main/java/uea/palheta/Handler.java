package uea.palheta;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import uea.palheta.model.aluno.Aluno;
import uea.palheta.model.aluno.AlunoDAO;
import uea.palheta.model.mensagem.MensagemDAO;
import uea.palheta.model.professor.Professor;
import uea.palheta.model.professor.ProfessorDAO;
import uea.palheta.model.tecnico.Tecnico;
import uea.palheta.model.tecnico.TecnicoDAO;

public class Handler implements Runnable {

    private Socket connection;
    private PrintWriter saida;

    public Handler(Socket connection) {
        this.connection = connection;
    }

    @Override
    public void run() {
        try {
            BufferedReader entrada = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            saida = new PrintWriter(connection.getOutputStream(), true);
            String mensagem;
            while (true) {
                mensagem = entrada.readLine();
                String response = processarComando(mensagem);
                if (response != null) {
                    saida.println(response);
                    saida.flush();
                }
            }
        
    } catch (SocketException e) {
        System.out.println("NINGUÉM ESTÁ ONLINE!");
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        closeConnection();
    }
    } 
    
    public void sendMessage(String message) {
        if (saida != null) {
            saida.println(message);
        }
    }

    public void closeConnection() {
        try {
            connection.close();
            ChatServer.removeClienteHandler(this);
            System.out.println("Cliente desconectado: " + connection.getInetAddress().getHostAddress());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getClientInfo() {
        return connection.getInetAddress().getHostAddress() + " -> " + connection.getPort();
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
                response = ChatServer.listarConexoes();
                return response;
            case 11:
                int index = Integer.parseInt(login);
                ChatServer.closeConnection(index);
                return null;
            case 12: 
                response = MensagemDAO.buscarMensagens();
                return response;
            case 13:
                if (ChatServer.offline.contains(login)) {
                    ChatServer.offline.remove(login);
                }
                if (!ChatServer.online.contains(login)) {
                    ChatServer.online.add(login);
                }
                return null;
            case 14:
                if (ChatServer.online.contains(login)) {
                    ChatServer.online.remove(login);
                }
                if (!ChatServer.offline.contains(login)) {
                    ChatServer.offline.add(login);
                }
                return null;
            case 15:
                String texto = "USUÁRIOS";
                for (String user : ChatServer.online) {
                    texto += ":%s (Online)".formatted(user);
                }
                for (String user : ChatServer.offline) {
                    texto += ":%s (Offline)".formatted(user);
                }
                return texto;
            case 16:
                ChatServer.sendToAll(login);
                MensagemDAO.cadastrarMensagem(login); 
                return null;
            default:
                return null;
    }
}
}