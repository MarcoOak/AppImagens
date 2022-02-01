package View;

import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import javax.jws.soap.SOAPBinding;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.*;

public class FEmpregado {
    private JPanel JPanelEmpregado;
    private JTextField textFieldIId;
    private JTextField textFieldSalario;
    private JButton salvarButton;
    private JButton procurarImagemButton;
    private JTextField textFieldNome;
    private JLabel LabelImage;
    private PreparedStatement pst;
   private Connection con;
    private String path=null;
    private byte[] UserImage;
    private ResultSet rs;

    public void Connection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bdempregados", "root", "1234");
            System.out.println("Success");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public FEmpregado() {
        procurarImagemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        procurarImagemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser imgChooser=new JFileChooser();
                imgChooser.showOpenDialog(null);
                File img=imgChooser.getSelectedFile();
                path=img.getAbsolutePath();
                BufferedImage image;
                try
                {
                    image= ImageIO.read(imgChooser.getSelectedFile());
                    ImageIcon imgIcon=new ImageIcon(new ImageIcon(image).getImage().
                            getScaledInstance(250,250, Image.SCALE_DEFAULT));
                    LabelImage.setIcon(imgIcon);
File imgg=new File(path);
                    FileInputStream fs=new FileInputStream(imgg);
                    ByteArrayOutputStream bos=new ByteArrayOutputStream();
                    byte[] buff=new byte[1024];
                    int nBytes_read=0;
                    while ((nBytes_read=fs.read(buff))!=-1) {
                        bos.write(buff, 0, nBytes_read);
                    }
                    UserImage=bos.toByteArray();

                }
                catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        salvarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome=textFieldNome.getText();
                double Salario=Double.valueOf(textFieldSalario.getText());
                try
                {
                    File file=new File(path);
                    FileInputStream fs=new FileInputStream(file);
                    Class.forName("com.mysql.jdbc.Driver");
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bdempregados", "root", "1234");
                    pst = con.prepareStatement("insert into empregados(nome,foto,salario)values(?,?,?)");
                    pst.setString(1, nome);
                    pst.setBytes(2, UserImage);
                    pst.setDouble(3, Salario);
                    pst.executeUpdate();
                    textFieldNome.setText("");
                    textFieldIId.setText("");
                    textFieldSalario.setText("");
                    JOptionPane.showMessageDialog(null,"Empregado salvado com sucesso");
                    LabelImage.setIcon(null);
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

    public void setVisible(boolean b)
    {
        JFrame frame=new JFrame("Consulta de Funcionarios");
        frame.setContentPane(new FEmpregado().JPanelEmpregado);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(b);
    }
}
