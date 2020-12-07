/*
Hecho por:
Kevin Daniel Contreras Hernandez A01635597
Jesus Riquelmer Gaxiola Higuera A01740223
 */
import javax.swing.*;
import java.awt.*;

public abstract class Carta extends JPanel {
    protected int cost,
                  tipo,
                  vida,
                  ataque;//0 neutral 1 fuego 2 ague 3 hierba
    int xv;
    boolean ent,
            at;

    public int getXv() {
        return xv;
    }

    public void setEnt(boolean ent) {
        this.ent = ent;
    }

    public boolean isEnt() {
        return ent;
    }

    public void setXv(int xv) {
        this.xv = xv;
    }

    public boolean canAtack(){
        return this.at;
    }

    public void setAt(boolean at) {
        this.at = at;
    }

    public Carta(int costo, int tipo, int vida, int atq){
        super();
        this.setLayout(null);
        this.cost=costo;
        this.tipo=tipo;
        this.vida=vida;
        this.ataque=atq;
        xv=150;
        at=true;
        this.setPreferredSize(new Dimension(160,150));
    }


    public abstract void atacar(Carta objetivo);

    public void atacar(Player p){
        p.setVida(p.getVida()-this.ataque);
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        g.drawImage(new ImageIcon(tipo+".jpg").getImage(),0,0,getWidth(),getHeight(),null);
        if(this instanceof Hechizo){
           g.drawImage(new ImageIcon("hech.jpg").getImage(),0,0,getWidth(),getHeight(),null);
        }
        g.setColor(Color.BLACK);
        g.drawImage(new ImageIcon("MANA.png").getImage(),0,0,20,20,null);
        g.drawString(this.getCost()+"",7,14);
        g.drawImage(new ImageIcon("ATTACK4.png").getImage(),0,this.getHeight()-20,20,20,null);
        g.setColor(Color.DARK_GRAY);
        g.drawString(this.getAtaque()+"",6,145);
        pintaVida(g);
    }

    public void pintaVida(Graphics g){
        g.drawImage(new ImageIcon("HP.png").getImage(),xv-10,130,20,20,null);
        g.setColor(Color.BLACK);
        g.drawString(this.getVida()+"",xv-4,143);
    }
}
