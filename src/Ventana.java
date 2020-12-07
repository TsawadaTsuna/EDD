/*
Hecho por:
Kevin Daniel Contreras Hernandez A01635597
Jesus Riquelmer Gaxiola Higuera A01740223
 */
import javax.swing.*;

public class Ventana extends JFrame  {
    private Tablero t;
    public Ventana(){
        super("Ragnarok TCG");
        t=new Tablero();
        this.add(t);
        this.pack();
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);

    }

}
