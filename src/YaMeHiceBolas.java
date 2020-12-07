//Autor: Kevin Contreras A01635597
//Clase: YaMeHiceBolas
//Fecha: 20/02/20
/*Observaciones:
Siento que lo hice bastante rapido
*/

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.Graphics;

public class YaMeHiceBolas extends JFrame {

    private int nivel;//Atributo para verificar cuantos circulos hacer

    public YaMeHiceBolas(){
        super("Circulos");
        this.setSize(720,700);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.nivel=Integer.parseInt(JOptionPane.showInputDialog("Escribir el nivel de profundidad"));
        this.setVisible(true);
    }

    public void pintaCirculos(Graphics g , int nivel , int x1 , int y1 , int largo){
        if(nivel==0){//Cuando el nivel es 0 se acaba la funcion
            g.drawOval(x1,y1,largo,largo);
        }else {//Si no es la ultima ejecucion dibuja el circulo y manda la llamada recursiva con los 2 circulos de enmedio
            g.drawOval(x1,y1,largo,largo);
            pintaCirculos(g,nivel-1,x1,y1+largo/4,largo/2);
            pintaCirculos(g,nivel-1,x1+(largo/2),y1+largo/4,largo/2);
        }
    }

    public void paint(Graphics g){
        super.paint(g);
        pintaCirculos(g,nivel,50,50,600);
    }

    public static void main(String[] args) {
        YaMeHiceBolas y= new YaMeHiceBolas();
    }
}
