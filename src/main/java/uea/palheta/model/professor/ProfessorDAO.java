package uea.palheta.model.professor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import uea.palheta.db.Conexao;

public class ProfessorDAO {
    public static String cadastrarProfessor(Professor p) {
        String sql = "INSERT INTO PROFESSOR (LOGIN, SENHA, TITULACAO) VALUES (?, ?, ?)";
        PreparedStatement ps = null;
        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, p.getLogin());
            ps.setString(2, p.getSenha());
            ps.setString(3, p.getTitulacao());

            ps.execute();
            ps.close();

            return "200 - Professor cadastrado com sucesso";
            
        } catch (SQLIntegrityConstraintViolationException e) {
            return "400 - Professor já cadastrado";
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String logarProfessor(String login, String senha) {

        String sql = "SELECT * FROM PROFESSOR WHERE LOGIN = ?";

        PreparedStatement ps = null;
        ResultSet rs = null;
        String loginBuscado = null;
        String senhaBuscada = null;

        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, login);
            rs = ps.executeQuery();

            while (rs.next()) {
                loginBuscado = rs.getString("login");
                senhaBuscada = rs.getString("senha");
            }

            if (loginBuscado == null) {
                return "400 - Não há registo";
            } else if (loginBuscado.equals(login) && senhaBuscada.equals(senha)) {
                return "200 - Professor logado";
            } else {
                return "400 - Senha Incorreta";
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void limparTabela() {
        String sql = "DELETE FROM PROFESSOR";
        PreparedStatement ps = null;
        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String listarProfessores() {
        String lista = "ID - LOGIN - SENHA - TITULAÇÃO";
        String sql = "SELECT * FROM PROFESSOR";

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            rs = ps.executeQuery();
            int i = 1;

            while (rs.next()) {
                String login = rs.getString("login");
                String senha = rs.getString("senha");
                String titulacao = rs.getString("titulacao");
                lista += ":%d - %s - %s - %s".formatted(i, login, senha, titulacao);
                i++;
            }

            return lista;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
