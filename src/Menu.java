/*
Hecho por:
Kevin Daniel Contreras Hernandez A01635597
Jesus Riquelmer Gaxiola Higuera A01740223
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JPanel{
    private JButton jugar,
            instruc,
            salir;
    public Menu(MenuVentana mv) {
        super();

        this.setPreferredSize(new Dimension(1000,1000));
        this.setLayout(null);

        this.jugar=new JButton("Jugar");
        this.instruc=new JButton("Instrucciones");
        this.salir=new JButton("Salir");

        this.jugar.setBounds(100,600,200,100);
        this.instruc.setBounds(400,600,200,100);
        this.salir.setBounds(700,600,200,100);

        this.jugar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //Abre el panel del juego
                setVisible(false);
                mv.dispose();
                Ventana ven=new Ventana();

            }
        });

        this.instruc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //setVisible(false);
                VentanaInt vi = new VentanaInt();
            }
        });

        this.salir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Termina la ejecucion del programa
                mv.dispose();
                System.exit(0);
            }
        });

        this.add(this.jugar);
        this.add(this.instruc);
        this.add(this.salir);

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(new ImageIcon("FondoMenu.jpg").getImage(), 0, 0, this.getWidth(),this.getHeight(),null);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial",1,90));
        g.drawString("Ragnarok TCG",150,400);
    }
}
