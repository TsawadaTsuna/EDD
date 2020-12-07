//Autor: Kevin Contreras A01635597
//Clase: Ordenamientos
//Fecha: 03/06/20
/*Observaciones:
Reutilize borrarInicio y borrarFin
*/
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListaEnlazada<E> implements Iterable<E>{
    private NodoLe<E> inicio,
            fin;
    protected int size;
    public ListaEnlazada(){
        this.inicio=this.fin=null;
        this.size=0;
    }

    public ListaEnlazada(E[] datos){
        this();
        if (datos.length!=0){
            this.inicio=this.fin=new NodoLe<E>(datos[0]);
            NodoLe<E> current=this.inicio;
            size+=1;
            for (int i=1;i<datos.length;i++){
                this.fin=current.next=new NodoLe<E>(datos[i]);
                current=current.next;
                size++;
            }
        }
    }

    public NodoLe<E> getFin() {
        return fin;
    }

    public E  borrarInicio(){
        //Borra el primer elemento y regresa el valor que tenia
        //Arroja NosuchElement Exception
        try {
            E tmp = this.inicio.valor;
            this.inicio = this.inicio.next;
            this.size--;
            return tmp;
        }catch (NullPointerException ex){
            throw new NoSuchElementException("No se pude borrar el inicio de una lista vacia");
        }
    }

    public E inicio() throws NoSuchElementException{
        try{
            return this.inicio.valor;
        }catch (NullPointerException ex){
            throw new NoSuchElementException("No se puede regresar el primer valor de una lista vacia");
        }
    }

    public E fin() throws NoSuchElementException{
        try{
            return this.fin.valor;
        }catch (NullPointerException ex){
            throw new NoSuchElementException("No se puede regresar el ultimo valor de una lista vacia");
        }
    }

    public int size(){
        return this.size;
    }

    public boolean isEmpty(){
        return this.size==0;
    }

    public void insetarInicio(E valor){
        this.inicio=new NodoLe<>(valor,this.inicio);
        if(this.fin==null)
            this.fin=this.inicio;
        this.size++;
    }

    public void insertarFin(E valor){
        if(this.size==0){
            this.insetarInicio(valor);
        }else {
            this.fin=this.fin.next=new NodoLe<>(valor);
            this.size++;
        }
    }

    //insetar en
    public void insertarEn(E valor, int pos){
        if(pos==0){
            this.insetarInicio(valor);
        }else if(pos<this.size-1) {
            NodoLe<E> current = this.inicio;
            for (int i = 0; i < pos-1; i++) {
                current = current.next;
            }
            NodoLe<E> nvo = new NodoLe<>(valor);
            nvo.next = current.next;
            current.next = nvo;
            this.size++;
        }else if(pos==this.size-1){
            this.insertarFin(valor);
        }else{
            throw new IndexOutOfBoundsException("No puedes insertar en la posicion "+pos+" en una lista de tamano "+this.size);
        }
    }

    //borrar en
    public E borrarEn(int pos){
        E tmp;
        if (pos==0){
            tmp=this.borrarInicio();
        }else if(pos<this.size-1) {
            NodoLe<E> current = this.inicio;
            for (int i = 0; i < pos-1; i++) {
                current = current.next;
            }
            tmp=current.next.valor;
            current.next = current.next.next;
            size--;
        }else if(pos==this.size-1){
            tmp=this.borrarFin();
        }else{
            throw new IndexOutOfBoundsException("No puedes eliminar la posicion "+pos+" de una lista de tamano "+this.size);
        }
        return tmp;
    }

    public E borrarFin(){
        try{
            E tmp = this.fin.valor;
            if(this.size>1) {
                NodoLe<E> current = this.inicio;
                for (int i = 0; i < this.size - 2; i++) {
                    current = current.next;
                }
                current.next = null;
                this.fin = current;
                this.size--;
            }else{
                this.borrarInicio();
            }
            return tmp;
        }catch (NullPointerException ex){
            throw new NoSuchElementException("No se puede borrar el ultimo elemento de una lista vacia");
        }
    }

    @Override
    public String toString() {
        String res="";
        NodoLe<E> current=this.inicio;
        for(int i=0;i<this.size;i++){
            res+=current.valor+", ";
            current=current.next;
        }
        return res;
    }

    public void setAt(E valor,int pos){
        if(pos>=0 &&pos<this.size) {
            NodoLe<E> current = this.inicio;
            for (int i = 0; i < pos; i++) {
                current = current.next;
            }
            current.valor = valor;
        }else {
            throw new IndexOutOfBoundsException("No se puede cambiar la posicion " + pos + " de una lista de tamano " + this.size);
        }

    }

    public static void main(String[] args) {
        Integer[] a ={7,6,5,4,3,2,1,12};
        ListaEnlazada<Integer> lista=new ListaEnlazada<>(a);
        Iterator<Integer> it=lista.iterator();
        while (it.hasNext()){
            int i=it.next();
            System.out.println(i);
            if(i==6){
                it.remove();
            }
        }
        for(Integer i:lista){
            System.out.println(i);
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            NodoLe<E> current=inicio,
                        prec=null,
                        pre=null;
            boolean callRemove=false;
            @Override
            public boolean hasNext() {
                return current!=null;
            }

            @Override
            public E next() {
                E val=current.valor;

                callRemove=true;
                pre=prec;
                prec=current;
                current = current.next;

                return val;
            }

            @Override
            public void remove() {
                if(callRemove) {
                    //current=current.next;
                    if(prec.valor.equals(inicio())){
                        borrarInicio();
                    }else if(prec.valor.equals(fin())){
                        borrarFin();
                    }else {
                        pre.next = current;
                        size--;
                    }
                    callRemove=false;
                }
            }
        };
    }
}

class NodoLe<E>{
    E valor;
    NodoLe<E> next;
    public E getValor() {
        return valor;
    }

    public void setValor(E valor) {
        this.valor = valor;
    }

    public NodoLe<E> getNext() {
        return next;
    }

    public void setNext(NodoLe<E> next) {
        this.next = next;
    }

    public NodoLe(E valor){
        this(valor,null);
    }

    public NodoLe(E valor, NodoLe<E> next){
        this.valor=valor;
        this.next=next;
    }

    @Override
    public String toString() {
        return this.valor.toString();
    }
}