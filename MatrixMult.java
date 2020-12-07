public class MatrixMult {
    double[][] rest;

    public void matrixMultiplication(double[][] A,double[][] B){
        if(A.length==A[0].length&&B.length==B[0].length&&A.length==B.length){
            rest= new double[A.length][A.length];
            double sum=0;
            for(int i = 0;i<A.length;i++){//Fila i
                for(int j=0;j<B.length;j++){//Columna j
                    for(int k =0;k<A.length;k++){
                        sum+=A[i][k]*B[k][j];
                    }
                    rest[i][j]=sum;
                    sum=0;
                }
            }
        }
    }

    public void printM(){
        for(int i=0;i<rest.length;i++){
            for(int j=0;j<rest.length;j++){
                System.out.print(rest[i][j]+" ");
            }
            System.out.println("");
        }
    }

    public static void main(String[] args) {
        double[][] a = new double[][]{{5,4,5},{4,5,3},{3,4,3}};
        double[][] b = new double[][]{{5,4,3},{4,5,4},{5,3,3}};
        MatrixMult m=new MatrixMult();
        m.matrixMultiplication(a,b);
        m.printM();
    }
}
