/*
Clase Principal

Hecho por:
Kevin Daniel Contreras Hernandez A01635597
Jesus Riquelmer Gaxiola Higuera A01740223
 */
import javax.swing.*;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MenuVentana extends JFrame {
    public MenuVentana() {
        super("Ragnarok TCG");
        Menu menu=new Menu(this);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(menu);

        this.pack();
        this.setResizable(false);
        this.setVisible(true);
    }

    public static void main(String[] args) throws FileNotFoundException, JavaLayerException {
        MenuVentana mv=new MenuVentana();
        Player apl = new Player(new FileInputStream("Glassy sky.mp3"));
        apl.play();
    }
}
