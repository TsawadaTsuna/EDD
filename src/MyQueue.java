import java.util.NoSuchElementException;

public class MyQueue<E> {
    private ListaEnlazada<E> cola;
    public MyQueue(){
        this.cola = new ListaEnlazada<E>();
    }

    public int size(){
        return this.cola.size();
    }

    public boolean isEmpty(){
        return this.cola.isEmpty();
    }

    public void flush(){
        this.cola=new ListaEnlazada<>();
        System.gc();
    }

    public void enqueue(E valor){
        this.cola.insertarFin(valor);
    }

    public E dequeue(){
        try {
            return this.cola.borrarInicio();
        }catch (NoSuchElementException ex){
            throw new NoSuchElementException("No se puede borrar de una queue vacia");
        }
    }

    public E next(){
        try {
            return this.cola.inicio();
        }catch (NoSuchElementException ex){
            throw new NoSuchElementException("No se puede obtener el siguiente elemento de una queue vacia");
        }
    }

    public static void main(String[] args) {
        MyQueue<String> que=new MyQueue<>();
        que.enqueue("Hola");
        que.enqueue("como");
        que.enqueue("Estas");
        que.enqueue("xd xd xd");
        while(!que.isEmpty()){
            System.out.println(que.dequeue());
        }
    }
}
