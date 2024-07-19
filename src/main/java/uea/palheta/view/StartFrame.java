package uea.palheta.view;

import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class StartFrame extends JFrame{
    private JRadioButton loginOption;
    private JRadioButton signupOption;
    private JButton pressButton;
    private ButtonGroup group;

    public StartFrame() {
        setTitle("Escolha o que quer fazer:");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 1, 10, 10));
        
        loginOption = new JRadioButton("Fazer Login");
        signupOption = new JRadioButton("Fazer Cadastro");
        pressButton = new JButton("Selecione");

        group = new ButtonGroup();
        group.add(loginOption);
        group.add(signupOption);

        add(loginOption);
        add(signupOption);
        add(pressButton);

        pressButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (loginOption.isSelected()){
                    dispose();
                    new LoginFrame().setVisible(true);
                } else if (signupOption.isSelected()) {
                    dispose();
                    new CadastroFrame().setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "NENHUMA OPÇÃO SELECIONADA");
                }
            }
        });
    }
}