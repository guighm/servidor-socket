package uea.palheta.model.tecnico;

import javax.swing.JFrame;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import uea.palheta.model.aluno.AlunosFrame;
import uea.palheta.model.professor.ProfessoresFrame;
import uea.palheta.view.KillFrame;
import uea.palheta.view.StatusFrame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class TecnicoOptions extends JFrame{
    private JRadioButton professoresOption;
    private JRadioButton alunosOption;
    private JRadioButton tecnicosOption;
    private JRadioButton statusOption;
    private JRadioButton killOption;
    private JButton pressButton;
    private ButtonGroup group;

    public TecnicoOptions() {
        setTitle("Escolha o que quer fazer:");
        setSize(400, 330);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 1, 10, 10));
        
        professoresOption = new JRadioButton("Listar Professores");
        alunosOption = new JRadioButton("Listar Alunos");
        tecnicosOption = new JRadioButton("Listar Técnicos");
        statusOption = new JRadioButton("Listar Status");
        killOption = new JRadioButton("Apagar Conexão");
        pressButton = new JButton("Selecione");

        group = new ButtonGroup();
        group.add(professoresOption);
        group.add(alunosOption);
        group.add(tecnicosOption);
        group.add(statusOption);
        group.add(killOption);

        add(professoresOption);
        add(alunosOption);
        add(tecnicosOption);
        add(statusOption);
        add(killOption);
        add(new JLabel());
        add(pressButton);

        pressButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (professoresOption.isSelected()) {
                    new ProfessoresFrame().setVisible(true);
                } else if (alunosOption.isSelected()) {
                    new AlunosFrame().setVisible(true);
                } else if (tecnicosOption.isSelected()) {
                    new TecnicosFrame().setVisible(true);
                } else if (statusOption.isSelected()) {
                    new StatusFrame().setVisible(true);
                } else if (killOption.isSelected()) {
                    new KillFrame().setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "NENHUMA OPÇÃO SELECIONADA");
                }
            }
        });
    }
}