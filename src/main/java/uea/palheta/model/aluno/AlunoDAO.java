package uea.palheta.model.aluno;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import uea.palheta.db.Conexao;

public class AlunoDAO {
    public static String cadastrarAluno(Aluno a) {
        String sql = "INSERT INTO ALUNO (LOGIN, SENHA, ANO_DE_INGRESSO) VALUES (?, ?, ?)";

        PreparedStatement ps = null;

        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, a.getLogin());
            ps.setString(2, a.getSenha());
            ps.setInt(3, a.getAnoDeIngresso());

            ps.execute();
            ps.close();

            return "200 - Aluno cadastrado com sucesso";

        } catch (SQLIntegrityConstraintViolationException e) {
            return "400 - Aluno já cadastrado";
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String logarAluno(String login, String senha) {

        String sql = "SELECT * FROM ALUNO WHERE LOGIN = ?";

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
                return "200 - Aluno logado";
            } else {
                return "400 - Senha Incorreta";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void limparTabela() {
        String sql = "DELETE FROM ALUNO";
        PreparedStatement ps = null;
        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String listarAlunos() {
        String lista = "ID - LOGIN - SENHA - ANO DE INGRESSO \n";
        String sql = "SELECT * FROM ALUNO";

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            rs = ps.executeQuery();
            int i = 1;

            while (rs.next()) {
                String login = rs.getString("login");
                String senha = rs.getString("senha");
                int ano = rs.getInt("ano_de_ingresso");
                lista += "%d - %s - %s - %d".formatted(i, login, senha, ano);
                i++;
            }

            return lista;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }
}