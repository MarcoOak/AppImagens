package View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FPrincipal {
    private JPanel JPanelPrincipal;
    private JButton consultarButton;
    private JButton adicionarEmpregadoButton;
    public static void main(String[] args)
    {
        JFrame frame=new JFrame("Gestao de Funcionarios");
        frame.setContentPane(new FPrincipal().JPanelPrincipal);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public FPrincipal() {
        adicionarEmpregadoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        consultarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
new FConsulta().setVisible(true);
            }
        });
        adicionarEmpregadoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FEmpregado().setVisible(true);
            }
        });
    }
}
