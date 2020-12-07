import java.util.NoSuchElementException;

public class MyABB<E extends Comparable<E>> {
    private MyNodoABB<E> raiz;
    private int size;

    public int getSize() {
        return size;
    }

    public boolean isEmpty(){
        return this.size==0;
    }

    public MyABB(){
        super();
    }

    public E buscar(E valor){
        if(this.isEmpty()){
            return null;
        }
        MyNodoABB<E> current=this.raiz;
        while(current!=null){
            if(current.dato.equals(valor)){
                return current.dato;
            }else if(current.dato.compareTo(valor)>0){
                current=current.izq;
            }else{
                current=current.der;
            }
        }
       throw new NoSuchElementException("No se puede regresar Un elemento que no esta en el arbol");
    }

    public void insertar(E valor){//Suponer que el elemento no exista
        if(this.isEmpty()){
            this.raiz=new MyNodoABB<>(valor);
            this.size++;
        }else{
            MyNodoABB<E> current=this.raiz,
                            pre=null;
            while(current!=null){
                pre=current;
                if(valor.compareTo(current.dato)>0){
                    current=current.der;
                }else{
                    current=current.izq;
                }
            }
            if(valor.compareTo(pre.dato)>0){
                pre.der=new MyNodoABB<>(valor);
            }else{
                pre.izq=new MyNodoABB<>(valor);
            }
            this.size++;
        }
    }

    public void insert(E valor){
        this.insert(this.raiz,valor);
        this.size++;
    }

    private MyNodoABB<E> insert(MyNodoABB<E> current,E valor){
        if(current==null){
            return new MyNodoABB<>(valor);
        }

        if (valor.compareTo(current.dato)<0){
            current.izq=insert(current.izq,valor);
        }if (valor.compareTo(current.dato)>0){
            current.der=insert(current.der,valor);
        }

        return current;
    }

    public E borrar(E valor){
        E tmp=null;
        if(this.isEmpty()){
            throw new NoSuchElementException("No se puede eliminar nada de un arbol vacio");
        }else if(this.size==1){
            if(this.raiz.dato.equals(valor)){
                tmp=this.raiz.dato;
                this.raiz=null;
            }else {
                throw new NoSuchElementException("No se puede eliminar un elemento que no existe");
            }
        }else {
            MyNodoABB<E> current=this.raiz,
                    pre=null;
            try {
            while(!current.dato.equals(valor)){
                pre=current;
                if(valor.compareTo(current.dato)>0){
                    current=current.der;
                }else{
                    current=current.izq;
                }
            }
            }catch (NullPointerException ex){
                throw new NoSuchElementException("No se puede eliminar un elemento que no existe");
            }
            tmp=current.dato;
            if(current.izq==null&&current.der==null){
                if(valor.compareTo(pre.dato)>0){
                    pre.der=null;
                }else{
                    pre.izq=null;
                }
            }else if(current.izq!=null&&current.der==null){
                if(valor.compareTo(pre.dato)>0){
                    pre.der=current.izq;
                }else{
                    pre.izq=current.izq;
                }
            }else if(current.izq==null&&current.der!=null){
                if(valor.compareTo(pre.dato)>0){
                    pre.der=current.der;
                }else{
                    pre.izq=current.der;
                }
            }else {
                MyNodoABB<E> p=current.izq,
                        pp=current;
                while (p.der!=null){
                    pp=p;
                    p=p.der;
                }
                current.dato=p.dato;
                if(p.izq==null){
                    pp.izq=null;
                }else{
                    pp.der=p.izq;
                }
            }
        }
        this.size--;
        return tmp;
        //Borrar un valor del arbol
        //Todos los casos
        //Hoja, un hijo o dos hijos
        //Predecesor
        //Caso especial: Borrar raiz o un dato que no existe
    }

    //Estudiar recorrido de arboles en preorden inorden y postorden y nivel

    public void inorden(){
        this.inorden(this.raiz);
        System.out.println();
    }

    private void inorden(MyNodoABB<E> current){
        if(current!=null) {
            this.inorden(current.izq);
            System.out.print(current.dato + ", ");
            this.inorden(current.der);
        }
    }

    public void preorden(){
        this.preorden(this.raiz);
        System.out.println();
    }

    private void preorden(MyNodoABB<E> current){
        if(current!=null) {
            System.out.print(current.dato + ", ");
            this.preorden(current.izq);
            this.preorden(current.der);
        }
    }

    public void postorden(){
        this.postorden(this.raiz);
        System.out.println();
    }

    private void postorden(MyNodoABB<E> current){
        if(current!=null) {
            this.postorden(current.izq);
            this.postorden(current.der);
            System.out.print(current.dato + ", ");
        }
    }

    public static void main(String[] args) {
        MyABB<Integer> a=new MyABB<>();
        a.insertar(21);
        a.insertar(13);
        a.insertar(33);
        a.insertar(10);
        a.insertar(18);
        a.insertar(25);
        a.insertar(40);
        a.insertar(29);
        a.insertar(27);
        a.insertar(30);
        a.postorden();
    }
}

class MyNodoABB<E extends Comparable<E>>{

    E dato;
    MyNodoABB<E> izq,
            der;

    public E getDato() {
        return dato;
    }

    public void setDato(E dato) {
        this.dato = dato;
    }

    public MyNodoABB<E> getIzq() {
        return izq;
    }

    public void setIzq(MyNodoABB<E> izq) {
        this.izq = izq;
    }

    public MyNodoABB<E> getDer() {
        return der;
    }

    public void setDer(MyNodoABB<E> der) {
        this.der = der;
    }

    public MyNodoABB(E dato){
        this(dato,null,null);
    }

    public MyNodoABB(E dato,MyNodoABB izq,MyNodoABB der){
        this.dato=dato;
        this.izq=izq;
        this.der=der;
    }

    @Override
    public String toString() {
        return this.dato+"";
    }
}
