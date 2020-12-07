/*
Hecho por:
Kevin Daniel Contreras Hernandez A01635597
Jesus Riquelmer Gaxiola Higuera A01740223
 */
import javax.swing.*;
import java.awt.*;

public class Player extends JPanel {
    private int vida,
                 manat,
                 mana;
    public Player(){
        super();
        this.setPreferredSize(new Dimension(100, 150));
        this.setOpaque(false);
        this.vida=20;
        this.manat=2;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        //g.setColor(Color.CYAN);
        // g.fillOval(0,0,160,160);
        g.drawImage(new ImageIcon("PLAYER.png").getImage(),10,10,140,140,null);
        g.setColor(Color.BLACK);
        g.drawImage(new ImageIcon("HP.png").getImage(),0,140,35,35,null);
        g.drawString(this.vida+"",10,160);
        g.drawImage(new ImageIcon("MANA.png").getImage(),145,135,35,35,null);
        g.drawString(this.mana+"",160,160);
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getManat() {
        return manat;
    }

    public void setManat(int manat) {
        this.manat = manat;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }
}
