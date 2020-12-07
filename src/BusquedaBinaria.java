public class BusquedaBinaria {
    public static <E extends Comparable<E>> int binarySearch(E[] datos, E valor){
        int min=0,
                max=datos.length-1,
                average;
        while (min<=max){
            average=(min+max)/2;
            if (datos[average].equals(valor)){
                return average;
            }else if(datos[average].compareTo(valor)<0){
                min=average+1;
            }else{
                max=average-1;
            }
        }
        return -1;
    }

    public static <E extends Comparable<E>> int binarySearchRe (E[] datos, E valor){
        return binarySearchRe(datos, valor,0, datos.length-1);
    }

    public static <E extends Comparable<E>> int binarySearchRe (E[] datos, E valor, int min, int max){
        int average=(min+max)/2;
        if (datos[average].equals(valor)){
            return average;
        }else if(datos[average].compareTo(valor)<0){
            return binarySearchRe(datos,valor,average+1,max);
        }else if(datos[average].compareTo(valor)>0){
            return binarySearchRe(datos,valor,min,average-1);
        }else{
            return -1;
        }
    }

    public static void main(String[] args) {
        Integer[] test={1,2,3,4,77,120,340,550,10000};
        Integer v=100;
        System.out.print(BusquedaBinaria.binarySearch(test,v));
    }
}
