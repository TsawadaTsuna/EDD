/*
Hecho por:
Kevin Daniel Contreras Hernandez A01635597
Jesus Riquelmer Gaxiola Higuera A01740223
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaInt extends JFrame {
    JPanel pan;
    JButton volver;
    public VentanaInt(){
        super("Ragnarok TCG instrucciones");
        pan=new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(new ImageIcon("int.jpg").getImage(),0,0,getWidth(),getHeight(),null);
            }
        };
        pan.setPreferredSize(new Dimension(750,550));
        volver=new JButton("Cerrar");
        volver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        pan.setLayout(null);
        volver.setBounds(325,500,100,50);
        pan.add(volver);
        this.add(pan);
        this.pack();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
    }

}
