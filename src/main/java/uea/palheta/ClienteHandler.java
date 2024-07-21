package uea.palheta;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.SocketException;
import uea.palheta.model.aluno.Aluno;
import uea.palheta.model.aluno.AlunoDAO;
import uea.palheta.model.professor.Professor;
import uea.palheta.model.professor.ProfessorDAO;
import uea.palheta.model.tecnico.Tecnico;
import uea.palheta.model.tecnico.TecnicoDAO;

public class ClienteHandler implements Runnable{

    private Socket connection;

    public ClienteHandler(Socket connection) {
        this.connection = connection;
    }

    @Override
    public void run() {
        ObjectInputStream entrada = null;
        PrintWriter saida = null;
        try {
            entrada = new ObjectInputStream(connection.getInputStream());
            saida = new PrintWriter(connection.getOutputStream(), true);
            String mensagem;
            do {
                mensagem = (String) entrada.readObject();
                System.out.println(mensagem);

                String response = processarComando(mensagem);
                saida.println(response);
        } while (!mensagem.contains("TERMINATE"));

        
    } catch (SocketException e) {
        System.out.println("NINGUÉM ESTÁ ONLINE!");
    } catch (IOException | ClassNotFoundException e) {
        e.printStackTrace();
    } finally {
        try {
            if (entrada != null) entrada.close();
            if (saida != null) saida.close();
            if (connection != null) connection.close();
            System.out.println("SERVIDOR ENCERRADO");
        } catch (IOException e) {
            e.printStackTrace();
        }

        }
    }  

    private String processarComando(String request) {
        String[] partes = request.split(":");

        int comando;
        try {
            comando = Integer.parseInt(partes[0]);
        } catch (NumberFormatException e) {
            return "Comando Inválido!";
        }

        String login = partes[1];
        String senha = partes[2];
        String response = "";

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
            //exibir status dos users
                return null;
            case 8:
            //enviar mensagem
                return null;
            default:
                return null;
    }
}
}