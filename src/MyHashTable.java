/*
Hecho por:
Kevin Daniel Contreras Hernandez A01635597
Jesus Riquelmer Gaxiola Higuera A01740223
 */
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class MyHashTable<K,V> {
    //Direccionamiento por encadenamiento
    private LinkedList<NodoHT<K,V>>[] tabla;
    private int size;

    public MyHashTable(){
        this(11);
    }

    public MyHashTable(int length){
        this.tabla=(LinkedList<NodoHT<K,V>>[])new LinkedList[length];
        this.size=0;
        for(int i=0;i<this.tabla.length;i++){
            this.tabla[i]=new LinkedList<NodoHT<K,V>>();
        }
    }

    private void rehashing(){
        //doble+

        LinkedList<NodoHT<K,V>>[] tmp=this.tabla;
        this.tabla=(LinkedList<NodoHT<K,V>>[])new LinkedList[this.tabla.length*2+1];
        this.size=0;
        for(int i=0;i<this.tabla.length;i++){
            this.tabla[i]=new LinkedList<NodoHT<K,V>>();
        }
        for (LinkedList<NodoHT<K,V>> list:tmp){
            for(NodoHT<K,V> nodo:list){
                this.put(nodo.llave,nodo.valor);
            }
        }

    }

    //Validar si la tabla no ha excedido el factor de carga
    //Factor maximo: 0.75
    public void put(K llave,V valor){
        //Tarea
        //Si el factor es mayor al permitido ->rehashing
        if(this.size/this.tabla.length>0.75){
            this.rehashing();
        }
        int pos = Math.abs(llave.hashCode()%this.tabla.length);
        this.tabla[pos].addFirst(new NodoHT<>(llave,valor));
        this.size++;
    }

    public V get(K llave){
        if(!this.isEmpty()) {
            int pos = Math.abs(llave.hashCode() % this.tabla.length);
            LinkedList<NodoHT<K, V>> list = this.tabla[pos];
            for (NodoHT<K, V> nodo : list) {
                if (nodo.llave.equals(llave)) {
                    return nodo.valor;
                }
            }
            throw new NoSuchElementException("No se encontro la llave " + llave);
        }
        throw new NoSuchElementException("No se puede obtener un elemento de una tabla vacia");
    }

    //Tarea
    public V delete(K llave){
        if(!this.isEmpty()) {
            int pos = Math.abs(llave.hashCode() % this.tabla.length);
            LinkedList<NodoHT<K, V>> list = this.tabla[pos];
            int i=0;
            V tmp;
            for (NodoHT<K, V> nodo : list) {
                if (nodo.llave.equals(llave)) {
                    tmp=nodo.valor;
                    list.remove(i);
                    return tmp;
                }
                i++;
            }
            throw new NoSuchElementException("No se encontro la llave " + llave);
        }
        throw new NoSuchElementException("No se puede borrar un elemento de una tabla vacia");
    }

    public boolean isEmpty(){
        return this.size==0;
    }

    //Tarea
    public boolean containsKey(K llave){
        int pos = Math.abs(llave.hashCode()%this.tabla.length);
        LinkedList<NodoHT<K,V>> list=this.tabla[pos];
        for(NodoHT<K,V> nodo:list){
            if (nodo.llave.equals(llave)) {
                return true;
            }
        }
        return false;
    }
    public static void main(String[] args) {

    }
}

class NodoHT<K,V>{
    K llave;
    V valor;

    public NodoHT(K llave, V valor) {
        this.llave = llave;
        this.valor = valor;
    }

    public K getLlave() {
        return llave;
    }

    public V getValor() {
        return valor;
    }

    public void setValor(V valor) {
        this.valor = valor;
    }
}