//Autor: Kevin Contreras A01635597
//Clase Recursion
//Fecha: 17/02/20
/*Observaciones:
Primero intente agregar un cuarto parametro en la segunda funcion hasta que se me ovurrio poner ese parametro como variable.
Me tarde mucho en resolver este problema
*/
public class Recursion {

    public static <E extends Comparable<E>> int buscaUltimo(E[ ] valores,E valor){
        if (valores.length==0)//Funcion de preparacion que checa que el arreglo contenga elementos e inicializa el primer posible valor de regreso en -1
            return -1;
        else
            return buscaUltimo(valores,valor,0);
    }

    public static <E extends Comparable<E>> int buscaUltimo(E[] valores,E valor, int pos){
        if(pos<valores.length){//La funcion evalua si la posicion esta dentro del arreglo, asi si lo recorremos y llegamos a pasarnos regresa -1
            if(valores[pos].compareTo(valor)==0){//Cuando encuentra el valor una vez, creamos una variable temporal para verificar si vuelve a aparcer el valor
                int siguiente= buscaUltimo(valores,valor,pos+1);
                if (siguiente>pos)//Si aparece otra vez regresamos la posicion mayor
                    return siguiente;
                else
                    return pos;
            }else
                return buscaUltimo(valores,valor,pos+1);//Si no s el valor que buscamos simplemente avanza
        }else
            return -1;
    }

    public static void impr(int val){
        if (val==1)
            System.out.print(val+" ");
        else{
            System.out.print(val+" ");
            impr(val-1);
            System.out.print(val+" ");
        }
    }
    public static void main(String[] args) {
        Integer[] prueba = {4,6,4,6,4,6,4,6,4,6,4,6,4};
        Integer vp = 4;
        System.out.println(Recursion.buscaUltimo(prueba,vp));
        impr(5);
    }
}
