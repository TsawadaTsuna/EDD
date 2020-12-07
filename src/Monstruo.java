import javax.swing.*;
import java.awt.*;
/*
Hecho por:
Kevin Daniel Contreras Hernandez A01635597
Jesus Riquelmer Gaxiola Higuera A01740223
 */
public class Monstruo extends Carta {

    public Monstruo(int costo, int tipo, int vida, int atq){
        super(costo,tipo,vida,atq);
    }


    @Override
    public void atacar(Carta objetivo) {//0 neutral 1 fuego 2 agua 3 hierba
        switch (this.tipo) {
            case 0:
                objetivo.setVida(objetivo.getVida() - this.ataque);
                this.vida = this.vida - objetivo.getAtaque();
                break;

            case 1:
                switch (objetivo.getTipo()) {
                    case 0:
                    case 1:
                        objetivo.setVida(objetivo.getVida() - this.ataque);
                        this.vida = this.vida - objetivo.getAtaque();
                        break;

                    case 2:
                        objetivo.setVida(objetivo.getVida() - this.ataque / 2);
                        this.vida = this.vida - objetivo.getAtaque() * 2;
                        break;

                    case 3:
                        objetivo.setVida(objetivo.getVida() - this.ataque * 2);
                        this.vida = this.vida - objetivo.getAtaque() / 2;
                        break;
                }
                break;
            case 2:
                switch (objetivo.getTipo()) {
                    case 0:
                    case 2:
                        objetivo.setVida(objetivo.getVida() - this.ataque);
                        this.vida = this.vida - objetivo.getAtaque();
                        break;

                    case 1:
                        objetivo.setVida(objetivo.getVida() - this.ataque * 2);
                        this.vida = this.vida - objetivo.getAtaque() / 2;
                        break;

                    case 3:
                        objetivo.setVida(objetivo.getVida() - this.ataque / 2);
                        this.vida = this.vida - objetivo.getAtaque() * 2;
                        break;
                }
                break;
            case 3:
                switch (objetivo.getTipo()) {
                    case 0:
                    case 3:
                      objetivo.setVida(objetivo.getVida() - this.ataque);
                      this.vida = this.vida - objetivo.getAtaque();
                      break;

                    case 1:
                       objetivo.setVida(objetivo.getVida() - this.ataque / 2);
                        this.vida = this.vida - objetivo.getAtaque() * 2;
                        break;
                    case 2:
                        objetivo.setVida(objetivo.getVida() - this.ataque * 2);
                        this.vida = this.vida - objetivo.getAtaque() / 2;
                        break;
                }
                break;
        }


    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(new ImageIcon("Rag"+this.cost+".png").getImage(),0,0,this.getWidth(),this.getHeight(),null);
        g.drawString(this.getCost()+"",7,14);
        g.setColor(Color.DARK_GRAY);
        g.drawString(this.getAtaque()+"",6,145);
        super.pintaVida(g);
    }

}
