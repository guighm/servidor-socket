package uea.palheta;

import uea.palheta.model.aluno.AlunoDAO;
import uea.palheta.model.professor.ProfessorDAO;
import uea.palheta.model.tecnico.TecnicoDAO;

public class Teste {
    public static void main(String[] args) {
        System.out.println(AlunoDAO.listarAlunos());
        System.out.println(ProfessorDAO.listarProfessores());
        System.out.println(TecnicoDAO.listarTecnicos());
    }
}