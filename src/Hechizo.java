/*
Hecho por:
Kevin Daniel Contreras Hernandez A01635597
Jesus Riquelmer Gaxiola Higuera A01740223
 */
import javax.swing.*;
import java.awt.*;

public class Hechizo extends Carta {
    public Hechizo(int costo, int atq) {
        super(costo, 0, 1, atq);
    }

    public void atacar(Carta objetivo){
        objetivo.setVida(objetivo.getVida() - this.ataque);
        this.vida=0;
    }

    @Override
    public void atacar(Player p) {
        super.atacar(p);
        this.vida=0;
    }


    public void pintaVida(Graphics g) {

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(new ImageIcon("SPELL.png").getImage(),0,0,this.getWidth(),this.getHeight(),null);
        g.drawString(this.getCost()+"",7,14);
        g.setColor(Color.DARK_GRAY);
        g.drawString(this.getAtaque()+"",6,145);
        super.pintaVida(g);
    }

}
