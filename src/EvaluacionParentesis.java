import java.util.NoSuchElementException;

public class EvaluacionParentesis {

    public static boolean evaluaParentesis(String exp){
        MyStack<String> stack=new MyStack<>();
        String[] tokens=exp.split(" ");
        //Si se encuentra un que abre se agrega a la pila
        //Si se encuentra uno que cierra, hacer pop y este debe hacer match con el mismo tipo de parentesis, si no no esta correcto
        //Si al hacer pop la pila esta vacia entonces error.
        //Al final si la pila esta vacia es correcto
        String pop;
        try {
            for (String t : tokens) {
                switch (t) {
                    case "(":
                    case "[":
                    case "{":
                        stack.push(t);
                        break;

                    case ")":
                        pop = stack.pop();
                        if (!"(".equals(pop)) {
                            System.out.println("No hacia match");
                            return false;
                        }
                        break;

                    case "]":
                        pop = stack.pop();
                        if (!"[".equals(pop)) {
                            System.out.println("No hacia match");
                            return false;
                        }
                        break;

                    case "}":
                        pop = stack.pop();
                        if (!"}".equals(pop)) {
                            System.out.println("No hacia match");
                            return false;
                        }
                        break;
                }
            }

            return stack.isEmpty();
        }catch (NoSuchElementException ex){
            System.out.println("El parentesis no tiene quien lo abra");
            return false;
        }
    }
    public static void main(String[] args) {
        String exp="( ( ( ) ) )";
        System.out.println(evaluaParentesis(exp));
    }
}
