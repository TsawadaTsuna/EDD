import java.util.NoSuchElementException;

public class MyStack<E> {
    private ListaEnlazada<E> stack;
    public MyStack(){
        stack=new ListaEnlazada<>();
    }
    public int size(){
        return this.stack.size();
    }

    public boolean isEmpty(){
        return this.stack.isEmpty();
    }

    public void flush(){
        this.stack=new ListaEnlazada<>();
        System.gc();
    }

    public void push(E valor){
        this.stack.insetarInicio(valor);
    }

    public E pop(){
        try{
            return this.stack.borrarInicio();
        }catch(NoSuchElementException ex){
            throw new NoSuchElementException("No se puede hacer pop de un stack vacio");
        }
    }

    public E top(){
        try{
            return this.stack.inicio();
        }catch(NoSuchElementException ex){
            throw new NoSuchElementException("No se puede hacer top de un stack vacio");
        }
    }

    public static void main(String[] args) {
        MyStack<Integer> stack=new MyStack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        while(!stack.isEmpty()){
            System.out.println(stack.pop());
        }
    }
}
