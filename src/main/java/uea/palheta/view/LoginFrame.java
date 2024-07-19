package uea.palheta.view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import uea.palheta.model.tecnico.Tecnico;
import uea.palheta.model.tecnico.TecnicoDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;
import javax.swing.JLabel;

public class LoginFrame extends JFrame{
    private JLabel loginLabel;
    private JTextField loginField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JButton pressButton;

    public LoginFrame() {
        setTitle("Fazer Login");
        setSize(250, 330);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 1, 10, 10));

        loginLabel = new JLabel("Login:");
        loginField = new JTextField();
        passwordLabel = new JLabel("Senha:");
        passwordField = new JPasswordField();
        pressButton = new JButton("Fazer Login");

        add(loginLabel);
        add(loginField);
        add(passwordLabel);
        add(passwordField);
        add(new JLabel());
        add(pressButton);

        pressButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String login = loginField.getText().replace(" ", "").toLowerCase();
                String senha = new String(passwordField.getPassword());
                senha.replace(" ", "");
                Tecnico tecnico1 = new Tecnico(login, senha);
                TecnicoDAO.logarTecnico(tecnico1);
            }
        });
    }
}