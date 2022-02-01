package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class FConsulta {
    private JPanel JPanelConsulta;
    private JTextField textFieldId;
    private JTextField textFieldNome;
    private JTextField TextFieldSalario;
    private JButton buttonAnterior;
    private JButton buttonProximo;
    private JButton buttonUltimo;
    private JButton buttonPrimeiro;
    private JLabel labelFoto;

    private Statement st;
    private Connection con;
    private String path=null;
    private byte[] UserImage;
    private ResultSet rs;
    public void Connection() {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bdempregados",
                    "root","1234");
            st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs=st.executeQuery("select idEmpregados,Nome,Salario, Foto from empregados ");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public FConsulta() {
        Connection();

        buttonAnterior.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (!rs.isFirst()) {
                        rs.previous();

                        textFieldId.setText(String.valueOf(rs.getInt("idEmpregados")));

                        textFieldNome.setText(rs.getString("Nome"));
                        TextFieldSalario.setText(String.valueOf(rs.getDouble("Salario")));
                        Blob blob = rs.getBlob("Foto");
                        byte[] imageBytes = blob.getBytes(1, (int)
                                blob.length());
                        ImageIcon imgIcon = new ImageIcon(new ImageIcon(imageBytes).
                                getImage().getScaledInstance(250, 250,Image.SCALE_DEFAULT));
                        labelFoto.setIcon(imgIcon);
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });

        buttonPrimeiro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    rs.first();

                    textFieldId.setText(String.valueOf(rs.getInt("idEmpregados")));

                    textFieldNome.setText(rs.getString("Nome"));
                    TextFieldSalario.setText(String.valueOf(rs.getDouble("Salario")));
                    Blob blob = rs.getBlob("Foto");
                    byte[] imageBytes = blob.getBytes(1, (int) blob.length());
                    ImageIcon imgIcon = new ImageIcon(new
                            ImageIcon(imageBytes).getImage().getScaledInstance(250, 250,
                            Image.SCALE_DEFAULT));
                    labelFoto.setIcon(imgIcon);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }

        });
        buttonUltimo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    rs.last();

                    textFieldId.setText(String.valueOf(rs.getInt("idEmpregados")));

                    textFieldNome.setText(rs.getString("Nome"));


                    TextFieldSalario.setText(String.valueOf(rs.getDouble("salario")));
                    Blob blob = rs.getBlob("Foto");
                    byte[] imageBytes = blob.getBytes(1, (int) blob.length());
                    ImageIcon imgIcon = new ImageIcon(new ImageIcon(imageBytes).
                            getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT));
                    labelFoto.setIcon(imgIcon);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
            });
        buttonProximo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(!rs.isLast()) {
                        rs.next();

                        textFieldId.setText(String.valueOf(rs.getInt("idEmpregados")));

                        textFieldNome.setText(rs.getString("Nome"));
                        TextFieldSalario.setText(String.valueOf(rs.getDouble("Salario")));
                        Blob blob = rs.getBlob("Foto");
                        byte[] imageBytes = blob.getBytes(1, (int) blob.length());
                        ImageIcon imgIcon = new ImageIcon(new ImageIcon(imageBytes).
                                getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT));
                        labelFoto.setIcon(imgIcon);
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
            }




    public void setVisible(boolean b)
    {
        JFrame frame=new JFrame("Consulta de Funcionarios");
        frame.setContentPane(new FConsulta().JPanelConsulta);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(b);
    }
}

