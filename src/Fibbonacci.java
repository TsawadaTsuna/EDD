public class Fibbonacci {
    public static long fibonacci(int n){
        if(n<=1)
            return 1;
        else
            return fibonacci(n-1)+fibonacci(n-2);
    }

    public static long fibini(int n){
        long[] res=new long[n+1];
        res[0]=res[1]=1;
        return fib(n,res);
    }

    private static long fib(int n,long[] res){
        if(res[n]>0)
            return res[n];
        else {
            return res[n]=fib(n-1,res)+fib(n-2,res);

        }

    }

    public static void main(String[] args) {
        long ini=System.nanoTime(),
                fin;
       System.out.println(Fibbonacci.fibini(10));
       fin=System.nanoTime();
       System.out.println((fin-ini)/1000000000.0);
    }
}
