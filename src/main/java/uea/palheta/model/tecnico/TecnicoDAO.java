package uea.palheta.model.tecnico;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import uea.palheta.db.Conexao;

public class TecnicoDAO {
    public static String cadastrarTecnico(Tecnico t){
        String sql = "INSERT INTO TECNICO (LOGIN, SENHA) VALUES (?, ?)";

        PreparedStatement ps = null;

        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, t.getLogin());
            ps.setString(2, t.getSenha());

            ps.execute();
            ps.close();

            return "200 - Técnico cadastrado com sucesso";

        } catch (SQLIntegrityConstraintViolationException e) {
            return "400 - Técnico já cadastrado";
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String logarTecnico(String login, String senha) {

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
                return "400 - Não há registo";
            } else if (loginBuscado.equals(login) && senhaBuscada.equals(senha)) {
                return "200 - Login realizado com sucesso";
            } else {
                return "400 - Senha Incorreta";
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
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
