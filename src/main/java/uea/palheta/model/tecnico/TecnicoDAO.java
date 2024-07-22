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

            return "200 - Tecnico cadastrado com sucesso";

        } catch (SQLIntegrityConstraintViolationException e) {
            return "400 - Tecnico ja cadastrado";
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
                return "400 - Nao ha registo";
            } else if (loginBuscado.equals(login) && senhaBuscada.equals(senha)) {
                return "200 - Tecnico logado";
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

    public static String listarTecnicos() {
        String lista = "ID - LOGIN - SENHA";
        String sql = "SELECT * FROM TECNICO";

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            rs = ps.executeQuery();
            int i = 1;

            while (rs.next()) {
                String login = rs.getString("login");
                String senha = rs.getString("senha");
                lista += ":%d - %s - %s".formatted(i, login, senha);
                i++;
            }

            return lista;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
