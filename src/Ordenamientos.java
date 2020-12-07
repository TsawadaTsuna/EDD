//Autor: Kevin Contreras A01635597
//Clase: Ordenamientos
//Fecha: 24/02/20
/*Observaciones:
Lo hubiera acabado en clase pero em el ultimo swap en vez de usar left use i
*/
import java.util.ArrayList;
import java.util.List;

public class Ordenamientos {

    public static <E extends Comparable<E>>void bubbleSort(E[] datos){
        for(int i=0;i<datos.length-1;i++){
            for(int j=0;j<datos.length-i-1;j++){
                if(datos[j].compareTo(datos[j+1])>0){
                    swap(datos,j,j+1);
                }
            }
        }
    }

    private static <E>void swap(E[] datos,int i, int j){
        E temp=datos[i];
        datos[i]=datos[j];
        datos[j]=temp;
    }

    public static <E>void imprimiArreglo(E[] a){
        for (E tmp:a) {
            System.out.print(tmp+" , ");
        }
    }

    public static <E extends Comparable<E>>void mergeSort(E datos[]){
        if(datos.length==0)//Verificacion de arreglo vacio
            return;
        mergeSort(datos,0,datos.length-1);
    }

    private static <E extends Comparable<E>>void mergeSort(E datos[],int prim,int ult){
        if(prim<ult){
            int central=(prim+ult)/2;

            mergeSort(datos,prim,central);
            mergeSort(datos,central+1,ult);
            mezcla(datos,prim,ult);
        }
    }

    private static <E extends Comparable<E>>void mezcla(E[] datos, int prim, int ult){
        int central=(prim+ult)/2;//Obtencion del punto central
        if(ult-prim==1){//Verifica cuando se pasan prim y ult como numeros consecutivos para evakuarlos directamente
            if (datos[prim].compareTo(datos[ult])>0){
                swap(datos,prim,ult);
            }
        }else {
            List<E> l = new ArrayList<>();//Creacion de la sublista de izquierda
            List<E> r = new ArrayList<>();//Sublista de derecha
            for (int i = prim; i <= central; i++) {//Llena la lista de izquierda hasta la mitad
                l.add(datos[i]);
            }
            for (int j = central+1; j <= ult; j++) {//LLena la lista de derecha con lo que falte
                r.add(datos[j]);
            }

            int i = 0,//Varible para indicar el indice de izquierda
                    j = 0,//Variable para indice de derecha
                    k = prim;//Variable para sobreescribir el arreglo
            while (i < l.size() && j < r.size()) {//Mientras que no se termine una lista se comparan los elementos entre ambas
                if (l.get(i).compareTo(r.get(j)) < 0) {
                    datos[k++] = l.get(i++);
                } else {
                    datos[k++] = r.get(j++);
                }
            }
            while (i < l.size()) {//Mete los elementos que falten de izquierda
                datos[k++] = l.get(i++);
            }
            while (j < r.size()) {//Mete los elementos que falted de derecha
                datos[k++] = r.get(j++);
            }
        }

    }

    public static <E extends Comparable<E>> void quickSort(E[] datos){
        quickSort(datos,0,datos.length-1);
    }

    private static <E extends Comparable<E>> void quickSort(E[] datos,int left, int rigth){
        if(left<rigth) {
            int posPivote = particion(datos, left, rigth);
            quickSort(datos, left, posPivote -1 );
            quickSort(datos, posPivote + 1, rigth);
        }
    }

    private static <E extends Comparable<E>> int particion(E[] datos,int left, int rigth){
        E piv = datos[left];
        int i = left+1;
        for(int j=left+1;j<=rigth;j++){
            if (datos[j].compareTo(piv)<0){
                swap(datos,i,j);
                i++;
            }
        }
        swap(datos,left,i-1);
        return i-1;
    }

    public static void main(String[] args) {
        Integer[] test={1,1,1,2,2,3,3,3,3,3,3,4,5,5,6};
        Ordenamientos.quickSort(test);
        Ordenamientos.imprimiArreglo(test);
    }
}
