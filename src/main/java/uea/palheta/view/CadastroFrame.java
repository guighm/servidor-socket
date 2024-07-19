package uea.palheta.view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import uea.palheta.model.tecnico.TecnicoDAO;
import uea.palheta.model.tecnico.Tecnico;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CadastroFrame extends JFrame{
    private JLabel loginLabel;
    private JTextField loginField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JButton pressButton;

    public CadastroFrame() {
        setTitle("Fazer Cadastro");
        setSize(300, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 1, 10, 10));

        loginLabel = new JLabel("Escolha um login:");
        loginField = new JTextField();
        passwordLabel = new JLabel("Escolha uma senha:");
        passwordField = new JPasswordField();
        pressButton = new JButton("Fazer Cadastro");

        add(loginLabel);
        add(loginField);
        add(passwordLabel);
        add(passwordField);
        add(new JLabel());
        add(pressButton);

        pressButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                dispose();
                String login = loginField.getText().replace(" ", "").toLowerCase();
                String senha = new String(passwordField.getPassword());
                senha.replace(" ", "");
                Tecnico tecnico1 = new Tecnico(login, senha);
                TecnicoDAO.cadastrarTecnico(tecnico1);
            }
        });
}
}