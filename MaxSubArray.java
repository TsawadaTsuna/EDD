public class MaxSubArray {
    public static int[] maximumSubArraySum(int[] E,int first, int last){

        if (last==first){
            return new int[]{first,last,sum(E,first,last)};
        }else{
            int mid=(first+last)/2;
            //Subarreglo izquierdo
            int[] l=maximumSubArraySum(E,first,mid);
            //Subarreglo derecho
            int[] r=maximumSubArraySum(E,mid+1,last);
            //Combinacion
            int comb=sum(E,l[0],r[1]);
            //Checa que es mayor un numero
            if(l[2]>comb){
                return l;
            }else if(r[2]>comb){
                return r;
            }else{
                return new int[]{l[0],r[1],comb};
            }

        }
    }

    public static int sum(int[] E,int first, int last){
        int sum=0;
        for(int i=first;i<=last;i++){
            sum+=E[i];
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] a = new int[]{1,3,2, 10,20,4,5,30};
        int[] r=maximumSubArraySum(a,0,a.length-1);
        for(int i=0;i<r.length;i++){
            System.out.print(r[i]+" ");
        }
    }
}
