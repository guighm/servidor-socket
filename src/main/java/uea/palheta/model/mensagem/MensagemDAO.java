package uea.palheta.model.mensagem;

import uea.palheta.db.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MensagemDAO {
    public static String buscarMensagens() {
        String sql = "SELECT * FROM MENSAGEM";

        PreparedStatement ps = null;
        ResultSet rs = null;
        String mensagens = "";

        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                mensagens += rs.getString("texto");
            }
            return mensagens;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void limparTabela() {
        String sql = "DELETE FROM MENSAGEM";
        PreparedStatement ps = null;
        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void cadastrarMensagem(String texto) {
        String sql = "INSERT INTO MENSAGEM (TEXTO) VALUE (?)";

        PreparedStatement ps = null;

        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, texto);
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
