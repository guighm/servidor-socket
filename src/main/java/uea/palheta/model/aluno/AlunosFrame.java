package uea.palheta.model.aluno;

import javax.swing.JFrame;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.awt.BorderLayout;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import uea.palheta.db.Conexao;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class AlunosFrame extends JFrame{
    public AlunosFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        try {
            String sql = "SELECT * FROM ALUNO";
            Statement s = Conexao.getConexao().createStatement();
            ResultSet rs = s.executeQuery(sql);

            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            String[] columnNames = new String[columnCount];
            for (int i = 1; i <= columnCount; i++) {
                columnNames[i-1] = metaData.getColumnName(i);
            }

            DefaultTableModel model = new DefaultTableModel(columnNames, 0);
            while (rs.next()) {
                Object[] row = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    row[i-1] = rs.getObject(i);
                }
                model.addRow(row);
            }

            JTable table = new JTable(model);
            add(new JScrollPane(table), BorderLayout.CENTER);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
}