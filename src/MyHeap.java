//Autor: Kevin Contreras A01635597
//Clase: MyHeap
//Fecha: 23/04/20
/*Observaciones:
Me tarde haciendo pruebas con los indices y no habia visto que si se llena se creaba uno nuevo pero ya esta tomado en cuenta
*/
import java.util.NoSuchElementException;

public class MyHeap<E extends Comparable<E>> {
    private E[] values;
    private int size; //Representa cu�ntos elementos hay guardados en el Heap

    public MyHeap(int length) {
        this.values=(E[])new Comparable[length];
        this.size=0;
    }

    public MyHeap(E[] data) {//Suponer que el arreglo se recibe lleno
        this.values=data;
        heapify();
        this.size=data.length;
    }

    public MyHeap() {
        this(10);
    }

    public void heapify() {
        //Tarea hacer que los datos del arreglo complan la condicion de heap.
        //O(nlog(n))
        this.heapify(this.size/2 -1);
    }

    private void heapify(int pos){
        int in=2*pos+1;
        E v;
        if(in<this.size&&in>=0) {
            v=values[in];
            if (values[2 * pos + 2].compareTo(v) > 0) {
                v = values[2 * pos + 2];
                in = 2 * pos + 2;
            }
            if (v.compareTo(values[pos]) > 0) {
                E tmp = v;
                values[in] = values[pos];
                values[pos] = tmp;
                this.heapify(in);
            }
            this.heapify(pos-1);
        }
    }

    public int getSize() {
        return this.size;
    }


    //Tarea
	public void insert(E data) {
		//O(log(n))
		if(this.size==values.length) {
			E[] tmp=(E[])new Comparable[this.size*2];
			//Pasar todos los elementos de values a tmp
            for (int i=0;i<this.values.length;i++){
                tmp[i]=this.values[i];
            }
			this.values=tmp;
		}
		this.values[this.size]=data;
		//Reacomodo
        int p=(this.size-1)/2,
                s=this.size ;
        while (this.values[p].compareTo(this.values[s])<0&&p!=0){
            E tmp=values[p];
            values[p]=values[s];
            values[s]=tmp;
            s=p;
            p=(p-1)/2;
        }
        if(this.values[1].compareTo(this.values[2])>0){
            if(this.values[1].compareTo(this.values[0])>0){
                E tmp=this.values[1];
                this.values[1]=this.values[0];
                this.values[0]=tmp;
            }
        }else{
            if(this.values[2].compareTo(this.values[0])>0){
                E tmp=this.values[2];
                this.values[2]=this.values[0];
                this.values[0]=tmp;
            }
        }
		this.size++;
	}


    public E remove() {
        //O(log(n))
        if(this.size>0) {
            //Completar
            E val=values[0];
            values[0]=values[this.size-1];
            this.size--;
            int p=0;
            int s;
            while(p*2+1<=this.size){
                s=p*2+1;
                if(values[s+1]!=null&&values[s+1].compareTo(values[s])>0){
                    s++;
                }
                if(values[p].compareTo(values[s])<0){
                    E tmp=values[p];
                    values[p]=values[s];
                    values[s]=tmp;
                }
                p=s;
            }
            return val;
        }
        else {
            throw new NoSuchElementException("No se puede borrar de un Heap vacio");
        }
    }

    public String toString() {
        String res="";
        for(int i=0;i<this.size;i++) {
            res+=this.values[i]+",";
        }
        return res;
    }

    public static void main(String[] args) {
        Integer[] a={10,70,50,40,30,20,60};
        MyHeap<Integer> h= new MyHeap<>(a);
        System.out.println(h.toString());
        h.insert(80);
        System.out.println(h.remove());
        System.out.println(h.toString());
    }
}
