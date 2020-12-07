/*
Hecho por:
Kevin Daniel Contreras Hernandez A01635597
Jesus Riquelmer Gaxiola Higuera A01740223
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class Tablero extends JPanel implements Runnable, MouseListener, MouseMotionListener, ActionListener {
    private Player jugador,
                    enemigo;
    private MyQueue<Carta> mazoj,
                           mazoe;
    private ArrayList<Carta> mano,
                             manoe,
                              zonamj,
                             zoname;
    private boolean turnoj,
                    robar,
                    selec;

    private MyHashTable<Integer,ListaEnlazada<Integer>> comene;

    JButton end;
    int cartaAt,
         atad;

    public Tablero(){
        super();
        this.setPreferredSize(new Dimension(1000,1000));
        this.setLayout(null);
        this.jugador=new Player();
        this.enemigo=new Player();
        this.turnoj=true;
        this.robar=true;
        this.selec=false;
        this.cartaAt=0;
        Image bt=new ImageIcon("EndTurn.png").getImage();
        ImageIcon btr=new ImageIcon(bt.getScaledInstance(50,50, Image.SCALE_SMOOTH));
        end=new JButton(btr);
        end.setOpaque(false);
        end.setContentAreaFilled(false);
        end.setBorderPainted(false);
        end.setMargin(new Insets(0,0,0,0));
        end.setBorder(null);
        end.setBounds(948,470,50,50);
        end.addActionListener(this);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.add(end);
        this.add(jugador);
        this.add(enemigo);
        jugador.setBounds(825,830,200,200);
        enemigo.setBounds(0,0,200,200);
        this.mazoj=new MyQueue<>();
        this.mazoe=new MyQueue<>();
        this.mano=new ArrayList<>();
        this.manoe=new ArrayList<>();
        this.zonamj=new ArrayList<>();
        this.zoname=new ArrayList<>();
        this.comene=new MyHashTable<>();
        //0 neutral 1 fuego 2 agua 3 hierba
        ListaEnlazada<Integer> listaNeutral=new ListaEnlazada<>();
        for(int n=0;n<4;n++){
            listaNeutral.insertarFin(n);
        }
        ListaEnlazada<Integer> listaFuego=new ListaEnlazada<>();
        for(int f=0;f<4;f++){
            listaFuego.insertarFin((f+3)%4);
        }
        ListaEnlazada<Integer> listaAgua= new ListaEnlazada<>();
        listaAgua.insertarFin(1);
        listaAgua.insertarFin(0);
        listaAgua.insertarFin(2);
        listaAgua.insertarFin(3);
        ListaEnlazada<Integer> listaHierba = new ListaEnlazada<>();
        for(int h=0;h<4;h++){
            listaHierba.insertarFin((h+2)%4);
        }
        comene.put(0,listaNeutral);
        comene.put(1,listaFuego);
        comene.put(2,listaAgua);
        comene.put(3,listaHierba);

        Random ran = new Random();
        for(int i=0;i<23;i++){
            int c=ran.nextInt(7)+1;
            Monstruo m=new Monstruo(c,ran.nextInt(4),c+1,ran.nextInt(c)+3);
            mazoj.enqueue(m);
            m.setBounds(-100,-100,10,10);
            this.add(m);
            if(i%3==0) {
                Hechizo h = new Hechizo(c, c);
                mazoj.enqueue(h);
                h.setBounds(-100, -100, 10, 10);
                this.add(h);
            }
            c=ran.nextInt(7)+1;
            m=new Monstruo(c,ran.nextInt(4),c+1,ran.nextInt(c)+3);
            mazoe.enqueue(m);
            m.setBounds(-100,-100,10,10);
            this.add(m);
            if(i%3==0) {
                Hechizo h = new Hechizo(c, c);
                mazoe.enqueue(h);
                h.setBounds(-100, -100, 10, 10);
                this.add(h);
            }
        }
        for(int j=0;j<5;j++){
            this.mano.add(mazoj.dequeue());
            this.manoe.add(mazoe.dequeue());
        }
        Thread t = new Thread(this);
        t.start();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if(this.jugador.getVida()<=0||this.enemigo.getVida()<=0||(mazoj.size()+mano.size()+zonamj.size())<=0||(mazoe.size()+manoe.size()+zoname.size())<=0){
            this.removeAll();
            this.pintaGover(g);
        }else{
            g.drawImage(new ImageIcon("pokerback.jpg").getImage(),0,0,getWidth(),getHeight(),null);
            g.setColor(Color.BLACK);
            g.drawImage(new ImageIcon("cartaBase.png").getImage(),0,850,100,150,null);
            g.drawImage(new ImageIcon("cartaBase.png").getImage(),900,0,100,150,null);
            pintaMano(g);
            g.setFont(new Font("Arial",1,20));
            if(turnoj)
                g.drawString("Tu turno",300,500);
            else
                g.drawString("Turno del Oponente",300,500);
            Graphics2D g2d = (Graphics2D)g;
            if(selec) {
                g2d.setStroke(new BasicStroke(10));
                g.drawRect(cartaAt * 160 + 100, 550, 160, 150);
            }
        }
    }

    public void pintaGover(Graphics g){
        if(this.jugador.getVida()<=0||this.enemigo.getVida()<=0) {
            if (enemigo.getVida() <= 0) {
                g.drawImage(new ImageIcon("Win.jpg").getImage(),0,0,getWidth(),getHeight(),null);
                g.setColor(Color.WHITE);
                g.setFont(new Font("Arial",1,40));
                g.drawString("Ganaste", 400, 500);
            } else {
                g.drawImage(new ImageIcon("lll.jpg").getImage(),0,0,getWidth(),getHeight(),null);
                g.setColor(Color.WHITE);
                g.setFont(new Font("Arial",1,40));
                g.drawString("Perdiste", 400, 500);
            }
        }
        if((mazoj.size()+mano.size()+zonamj.size())<=0||(mazoe.size()+manoe.size()+zoname.size())<=0){
            if((mazoe.size()+manoe.size()+zoname.size())<=0){
                g.drawImage(new ImageIcon("Win.jpg").getImage(),0,0,getWidth(),getHeight(),null);
                g.setColor(Color.WHITE);
                g.setFont(new Font("Arial",1,40));
                g.drawString("Ganaste", 400, 500);
            }else{
                g.drawImage(new ImageIcon("noCards.jpg").getImage(),0,0,getWidth(),getHeight(),null);
                g.setColor(Color.WHITE);
                g.setFont(new Font("Arial",1,40));
                g.drawString("Perdite", 400, 500);
            }
        }

    }

    public void checkDeath(ArrayList<Carta> zona){
        for(int i =0;i<zona.size();i++){
            if(zona.get(i).getVida()<=0){
                zona.get(i).setBounds(1200,1200,100,100);
                zona.remove(i);
            }
        }
    }

    public void pintaZona(){
        int con=0;
        for(int i =0;i<zonamj.size();i++){
            checkDeath(zonamj);
            if(i<zonamj.size()) {
                zonamj.get(i).setBounds(100 + (800 / 5 * con), 550, 800 / 5, 150);
                zonamj.get(i).setXv(150);
                con++;
            }
        }
        con=0;
        for(int j=0;j<zoname.size();j++){
            checkDeath(zoname);
            if(j<zoname.size()) {
                zoname.get(j).setBounds(100 + (800 / 5 * con), 300, 800 / 5, 150);
                zoname.get(j).setXv(150);
                con++;
            }
        }
    }

    public void iniAt(ArrayList<Carta> zona){
        for(int i =0;i<zona.size();i++){
            zona.get(i).setAt(true);
        }
    }

    public void pintaMano(Graphics g){
        int con=0;
        for(Carta c:mano){
            if(c.isEnt())
                c.setBounds(250+(500/this.mano.size()*con),820,500/this.mano.size(),150);
            else
                c.setBounds(250+(500/this.mano.size()*con),850,500/this.mano.size(),150);
            c.setXv(500/this.mano.size()-10);
            con++;
        }
        con=0;
        for(Carta c:manoe){

            g.drawImage(new ImageIcon("cartaBase.png").getImage(),250+(500/this.manoe.size()*con),0,500/this.manoe.size(),150,null);
            con++;
        }
    }

    @Override
    public void run() {
        while(this.jugador.getVida()>0&&this.enemigo.getVida()>0&&(mazoj.size()+mano.size()+zonamj.size())>0&&(mazoe.size()+manoe.size()+zoname.size())>0){
            this.repaint();
            //Cambio
            pintaZona();
            if(turnoj&&robar){
                if(!mazoj.isEmpty()){
                    this.mano.add(this.mazoj.dequeue());
                    robar=false;
                }
                iniAt(zonamj);
                if(this.jugador.getManat()<10)
                    this.jugador.setManat(this.jugador.getManat()+1);
                this.jugador.setMana(this.jugador.getManat());
            }else if(!turnoj&&robar){
                if(this.mazoe.size()>0){
                    this.manoe.add(this.mazoe.dequeue());
                }
                if(this.enemigo.getManat()<10)
                    this.enemigo.setManat(this.enemigo.getManat()+1);
                this.enemigo.setMana(this.enemigo.getManat());

                for(int c=manoe.size()-1;c>=0;c--){
                    if(enemigo.getMana()>0&&zoname.size()<5){
                        if(manoe.get(c).getCost()<=enemigo.getMana()){
                            enemigo.setMana(enemigo.getMana()-manoe.get(c).getCost());
                            zoname.add(manoe.remove(c));
                            pintaZona();
                            try{
                                Thread.sleep(1000);
                            }catch (InterruptedException ex){
                                System.out.println("Me pare");
                            }
                        }
                    }
                }
                iniAt(zoname);
                for(int c=zoname.size()-1;c>=0;c--){
                    ListaEnlazada<Integer> tmp=comene.get(zoname.get(c).getTipo());
                    if(!zonamj.isEmpty()) {
                        for (Integer pri : tmp) {
                            for(int cj=zonamj.size()-1;cj>=0;cj--){
                                pintaZona();
                                System.out.println("Valor de c: "+c);
                                System.out.println("Valor de cj: "+cj);
                                System.out.println("Tamano de zona enemiga asociada a c: "+zoname.size());
                                System.out.println("Tamano de zona aliada asociada a cj: "+zonamj.size());
                                if(c<zoname.size()&&cj<zonamj.size()){
                                    if(zonamj.get(cj).getTipo()==pri&&zoname.get(c).canAtack()) {
                                        zoname.get(c).atacar(zonamj.get(cj));
                                        zoname.get(c).setAt(false);
                                        try {
                                            Thread.sleep(500);
                                        } catch (InterruptedException ex) {
                                            System.out.println("Me pare");
                                        }
                                    }
                                }
                            }
                        }
                    }else{
                        zoname.get(c).atacar(jugador);
                        zoname.get(c).setAt(false);
                    }
                }
                turnoj=true;
                robar=true;
            }
            if(cartaAt<zonamj.size()) {
                if (zonamj.get(cartaAt).getVida() <= 0) {
                    zonamj.get(cartaAt).setBounds(1050, 1050, 160, 150);
                    zonamj.remove(cartaAt);
                }
            }
            if(atad<zoname.size()) {
                if (zoname.get(atad).getVida() <= 0) {
                    zoname.get(atad).setBounds(1050, 1050, 160, 150);
                    zoname.remove(atad);
                }
            }
        }
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x,y,pos;
        x=e.getX();
        y=e.getY();
        if(x>250&&x<750&&y>850&&y<1000&&turnoj&&!mano.isEmpty()&&zonamj.size()<5){
            pos=(x-250)/(500/mano.size());
            if(jugador.getMana()>=mano.get(pos).getCost()){
                jugador.setMana(jugador.getMana()-mano.get(pos).getCost());
                mano.get(pos).setAt(true);
                zonamj.add(mano.remove(pos));
            }
        }else if(x>100&&x<900&&y>550&&y<700&&turnoj){
            this.cartaAt=(e.getX()-100)/(800/5);
            if(this.cartaAt<this.zonamj.size()){
                if(this.zonamj.get(this.cartaAt).canAtack()){
                    this.selec=true;

                }
            }
        }else if(x>100&&x<900&&y>300&&y<450&&turnoj&&selec){
            if(this.zoname.size()>0) {
                pos = (e.getX() - 100) / (800 / 5);
                System.out.println("pos " + pos);
                System.out.println("selec en enemigo " + selec);
                if (cartaAt < this.zonamj.size()) {
                    this.zonamj.get(cartaAt).atacar(this.zoname.get(pos));
                    this.zonamj.get(cartaAt).setAt(false);
                    atad = pos;
                }
            }else{
                if (cartaAt < this.zonamj.size()) {
                    this.zonamj.get(cartaAt).atacar(enemigo);
                    this.zonamj.get(cartaAt).setAt(false);
                }
            }
            this.selec=false;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        int x,y,pos;
        x=e.getX();
        y=e.getY();
        if(x>250&&x<740&&y>850&&y<1000) {
            try {
                pos = (x - 250) / (500 / mano.size());
                mano.get(pos).setEnt(true);
                //System.out.println(pos);
                for (int i = 0; i < mano.size(); i++) {
                    if (i != pos) {
                        mano.get(i).setEnt(false);
                    }
                }
            }catch (ArithmeticException ex){

            }
        }else {
            for (Carta c : mano) {
                c.setEnt(false);
            }
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(turnoj){
            turnoj=false;
        }else{
        }
        robar=true;
    }
}
