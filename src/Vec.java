import java.util.Vector;

public class Vec extends Habitacion {
    public Vec(){
        super(1);
    }
    public static void main(String[] args) {
        Vec uno = new Vec();
        uno.checkIn("Kevin",1,3,550.35);
        System.out.println(uno.getTarifaBase());
        System.out.println(uno);
        uno.cargar(120.2);
        System.out.println(uno);
        uno.checkOut();
        System.out.println(uno.getDisponible());
    }
}
