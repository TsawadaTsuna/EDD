public class Reinas {
    private int[] tablero;
    public Reinas(int n){
        this.tablero = new int[n];
    }

    private void imprimeTablero(){
        for(int j =0;j<this.tablero.length;j++){
            System.out.println(this.tablero[j]+" ");
        }
        System.out.println();
    }

    private boolean valida(int fila,int columna){
        for(int i=0;i<columna;i++){
            if(this.tablero[i]==fila){
                return false;
            }

            if(Math.abs(columna-i)==Math.abs(this.tablero[i]-fila)){
                return false;
            }
        }
        return true;
    }

    public void busca( ){
        busca(0);
    }

    private void busca(int columna){
        for(int i=0;i<this.tablero.length;i++){
            if(valida(i,columna)){
                this.tablero[columna]=i;
                if(columna==this.tablero.length-1) {
                    imprimeTablero();
                }
            }else {
                busca(columna + 1);
            }
        }
    }

    public static void main(String[] args) {
        Reinas r=new Reinas(4);
        r.busca();
    }
}
