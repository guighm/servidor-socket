package uea.palheta.model.aluno;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import javax.swing.JOptionPane;
import uea.palheta.view.LoginFrame;
import uea.palheta.db.Conexao;

public class AlunoDAO {
    public static void cadastrarAluno(Aluno a) {
        String sql = "INSERT INTO ALUNO (LOGIN, SENHA, ANO_DE_INGRESSO) VALUES (?, ?, ?)";

        PreparedStatement ps = null;

        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, a.getLogin());
            ps.setString(2, a.getSenha());
            ps.setInt(3, a.getAnoDeIngresso());

            ps.execute();
            ps.close();

            JOptionPane.showMessageDialog(null, "Aluno cadastrado com sucesso!");

        } catch (SQLIntegrityConstraintViolationException e) {
            JOptionPane.showMessageDialog(null, "Aluno já cadastrado!", "ERRO", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void logarAluno(Aluno a) {
        String login = a.getLogin();
        String senha = a.getSenha();

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
                JOptionPane.showMessageDialog(null, "Não há registro!");
                new LoginFrame().setVisible(true);
            } else if (loginBuscado.equals(login) && senhaBuscada.equals(senha)) {
                JOptionPane.showMessageDialog(null, "Login realizado com sucesso");
            } else {
                JOptionPane.showMessageDialog(null, "Senha Incorreta");
                new LoginFrame().setVisible(true);
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
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
}