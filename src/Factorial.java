public class Factorial {
    public long factorial(int n){
        if(n<2){
            return 1;
        }else{
            return n*factorial(n-1);
        }
    }

    public static void main(String[] args) {
        Factorial f=new Factorial();
        System.out.println(f.factorial(5));
    }
}
