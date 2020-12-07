import java.util.ArrayList;

public class PruebaArr {
    private ArrayList<Integer> l;
    public PruebaArr(){
        l=new ArrayList<>();
    }

    public static void main(String[] args) {
        PruebaArr p=new PruebaArr();
        p.l.add(0);
        p.l.add(1);
        p.l.add(2);
        p.l.add(3);
        System.out.println(p.l.remove(1));
        System.out.println(p.l.remove(2));
        System.out.println(p.l.remove(1));
    }
}
