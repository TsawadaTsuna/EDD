import javax.swing.*;
import java.awt.*;

public class FractalSG extends JFrame {
    private Point a = new Point(550,100),
                b = new Point(50,400),
                c = new Point(750,550);
    private int nivel;

    public FractalSG(){
        super("Fractal Sierpinski Gasket");
        this.setSize(800,600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.nivel=Integer.parseInt(JOptionPane.showInputDialog("Escribir el nivel de profundidad"));
        this.setVisible(true);
    }

    private void pintaLineas(Graphics g, Point a, Point b){
        g.drawLine(a.x,a.y,b.x,b.y);
    }

    private Point puntoMedio(Point a, Point b){
        return new Point((b.x+a.x)/2,(a.y+b.y)/2);
    }

    private void pintaTriangulos(Graphics g, int n,Point a,Point b, Point c){
        if(n==0){
            this.pintaLineas(g,a,b);
            this.pintaLineas(g,a,c);
            this.pintaLineas(g,b,c);
        }else{
            Point mab=this.puntoMedio(a,b),
                  mbc=this.puntoMedio(b,c),
                  mca=this.puntoMedio(c,a);
            pintaTriangulos(g,n-1,a,mab,mca);
            pintaTriangulos(g,n-1,b,mab,mbc);
            pintaTriangulos(g,n-1,c,mbc,mca);
        }
    }

    public void paint(Graphics g){
        super.paint(g);
        pintaTriangulos(g,this.nivel,this.a,this.b,this.c);
    }

    public static void main(String[] args) {
        FractalSG f=new FractalSG();

    }
}
