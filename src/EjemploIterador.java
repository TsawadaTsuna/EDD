import java.util.Iterator;
import java.util.NoSuchElementException;

public class EjemploIterador implements Iterable<Integer> {
    private Integer[] valores;
    private int size;

    public EjemploIterador(Integer[] valores){
        this.valores=valores;
        this.size=valores.length;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            int pos=0;
            boolean callRemove=false;

            @Override
            public boolean hasNext() {
                return this.pos<size;
            }

            @Override
            public Integer next() {
                if(hasNext()) {
                    callRemove=true;
                    return valores[pos++];
                }else
                    throw new NoSuchElementException("No hay elemento por regresar");
            }

            @Override
            public  void remove() {
                if(callRemove){
                    for(int i=pos;i<size;i++){
                        valores[i-1]=valores[i];
                    }
                    valores[pos]=null;
                    pos--;
                    callRemove=false;
                    size--;
                }
            }
        };
    }

    public static void main(String[] args) {
        EjemploIterador v=new EjemploIterador(new Integer[] {1,2,3,4,5,6,7,8});
        Iterator<Integer> it=v.iterator();
        while(it.hasNext()){
            Integer t=it.next();
            System.out.println(t);
            if(t.equals(5))
                it.remove();
        }
        for(Integer i:v){
            System.out.println(i);
        }
    }
}
