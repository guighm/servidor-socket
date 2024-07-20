package uea.palheta.testes;

import uea.palheta.model.aluno.Aluno;
import uea.palheta.model.aluno.AlunoDAO;
import uea.palheta.model.mensagem.MensagemDAO;
import uea.palheta.model.professor.Professor;
import uea.palheta.model.professor.ProfessorDAO;
import uea.palheta.model.tecnico.Tecnico;
import uea.palheta.model.tecnico.TecnicoDAO;

public class Preparacao {
    
    public static void inserirDados() {
        Aluno aluno1 = new Aluno("nunes", "xcaboquinho", 2024);
        Professor professor1 = new Professor("palheta", "1234", "Doutora");
        Tecnico tecnico1 = new Tecnico("tecnico001", "3579");

        AlunoDAO.cadastrarAluno(aluno1);
        ProfessorDAO.cadastrarProfessor(professor1);
        TecnicoDAO.cadastrarTecnico(tecnico1);
    }

    public static void limparDados() {
        AlunoDAO.limparTabela();
        ProfessorDAO.limparTabela();
        TecnicoDAO.limparTabela();
        MensagemDAO.limparTabela();
    }
}
