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
        try {
            if (connection != null) connection.close();
            synchronized (ServidorSocket.clienteSockets) {
                ServidorSocket.clienteSockets.remove(connection);
            }
            System.out.println("Cliente desconetado!");
            if (entrada != null) entrada.close();
            if (saida != null) saida.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

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
            case 11:
                // int tamanho = ServidorSocket.apelidos.size();
                String texto = "";
                // for (int i = 1; i < tamanho; i++) {
                //     String elemento = ServidorSocket.apelidos.get(i);
                //     texto += "%s:".formatted(elemento);
                // }
                // String ultimo =  ServidorSocket.apelidos.get(tamanho);
                // texto += "%s".formatted(ultimo);
                return texto;
            case 12:
                System.out.println(login);
                MensagemDAO.cadastrarMensagem(login); 
                return null;
            case 13:
                // int posicao = Integer.parseInt(login);
                // Socket conexao = ServidorSocket.connections.get(posicao);
                // conexao.close();
                // ServidorSocket.connections.remove(posicao);
                // ServidorSocket.apelidos.remove(posicao);
                return null;
            default:
                return null;
    }
}
}