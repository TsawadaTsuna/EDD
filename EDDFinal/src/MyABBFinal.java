//Kevin Daniel Contreras Hernandez
//06/06/20
//Clase MyABBFinal
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class MyABBFinal<E extends Comparable<E>> extends MyABBFinalPadre<E> implements Iterable<E> {

    public MyABBFinal(){
        super();
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            Queue<NodoABB<E>> cola = new LinkedList<>();
            E val;
            {
                cola.add(raiz);
            }

            @Override
            public boolean hasNext() {
                return !cola.isEmpty();
            }

            @Override
            public E next() {
                    NodoABB<E> tmp=cola.peek();
                    if(tmp.izq!=null) {
                        cola.add(tmp.izq);
                    }
                    if(tmp.der!=null){
                        cola.add(tmp.der);
                    }
                    val= cola.poll().dato;

                return val;
            }
        };
    }

    public static void main(String[] args) {
        MyABBFinal<Integer> a=new MyABBFinal<>();
        a.insertar(50);
        a.insertar(98);
        a.insertar(30);
        a.insertar(80);
        a.insertar(15);
        a.insertar(45);
        a.insertar(65);

        for(Integer i:a){
            System.out.println(i);
        }
    }
}
