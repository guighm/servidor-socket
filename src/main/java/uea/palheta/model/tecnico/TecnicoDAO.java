package uea.palheta.model.tecnico;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import javax.swing.JOptionPane;
import uea.palheta.db.Conexao;
import uea.palheta.view.LoginFrame;

public class TecnicoDAO {
    public static void cadastrarTecnico(Tecnico t){
        String sql = "INSERT INTO TECNICO (LOGIN, SENHA) VALUES (?, ?)";

        PreparedStatement ps = null;

        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, t.getLogin());
            ps.setString(2, t.getSenha());

            ps.execute();
            ps.close();

            JOptionPane.showMessageDialog(null, "Técnico cadastrado com sucesso!");

        } catch (SQLIntegrityConstraintViolationException e) {
            JOptionPane.showMessageDialog(null, "Técnico já cadastrado!", "ERRO", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void logarTecnico(Tecnico t) {
        String login = t.getLogin();
        String senha = t.getSenha();

        String sql = "SELECT * FROM TECNICO WHERE LOGIN = ?";

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
        String sql = "DELETE FROM TECNICO";
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
