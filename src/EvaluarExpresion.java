//Autor: Kevin Contreras A01635597
//Clase: EvaluarExpresion
//Fecha: 23/03/20
/*Observaciones:
Use estos videos de referencia:
Este para la convercion:
https://www.youtube.com/watch?v=5zhXm3J9xdE

Y este para la evaluacion:
https://www.youtube.com/watch?v=J5_vbih7McA
*/
public class EvaluarExpresion {
    private String expresionInfija;

    public void setExpresionInfija(String expresionInfija) {
        this.expresionInfija = expresionInfija;
    }

    public EvaluarExpresion(String exp){
        expresionInfija=exp;
    }

    public double evaluaExpresion(){
        String epo=this.expresionPostfijo();
        String[] elem=epo.split(" ");
        MyStack<Double> stack=new MyStack<>();
        double op1, op2, res;
        //Se genera un string en notacion postfija y se checa cada elemento
        //Si es un numero se convierte a Double y se agrega al stack
        //Si es una operacion se sacan los 2 ultimpos elementos del stack y se evalua segun la operacio
        //El resultado se vuelve a meter al stack
        //Al finalizar se regresa el ultimo valor del stack
        for(String e: elem){
            switch (e){
                case "+":
                    op2=stack.pop();
                    op1=stack.pop();
                    res=op1+op2;
                    stack.push(res);
                    break;
                //Puse 2 guiones por que me detecta diferente si uso el guion en el teclado espanol o ingles
                case "–":
                case "-":
                    op2=stack.pop();
                    op1=stack.pop();
                    res=op1-op2;
                    stack.push(res);
                    break;
                case "*":
                    op2=stack.pop();
                    op1=stack.pop();
                    res=op1*op2;
                    stack.push(res);
                    break;
                case "/":
                    op2=stack.pop();
                    op1=stack.pop();
                    res=op1/op2;
                    stack.push(res);
                    break;
                case "^":op2=stack.pop();
                    op1=stack.pop();
                    res=Math.pow(op1,op2);
                    stack.push(res);
                    break;
                default:
                    stack.push(Double.parseDouble(e));
                    break;
            }
        }
        return stack.pop();
    }

    public String expresionPostfijo(){
        String res="";
        String[] term = this.expresionInfija.split(" ");
        MyStack<String> stack = new MyStack<>();
        //Verifica cada elemento
        //Si es un numero lo pone directamente
        //Si es un operador verifica la precedencia y decide que hacer:
        //1. Si el top es de la misma procedencia se hace un pop y luego se mete el operador nuevo
        //2. Si el top es de mayor precedencia se vacia el stack y luego se hace push
        //3. Si el top es de menor precedencia se hace un push
        //En caso de parentesis que abre nadamas se mete al stack
        //En caso de parentesis que cierra se vacia el stack hasta el parentesis que abre
        //Al terminar de revisar todos los elementos vacia la pila para poner las operaciones restantes
        //Se regresa todo el string con un trim para que facilite la evaluacion
        for(String t:term){
            switch (t){
                //Puse 2 guiones por que me detecta diferente si uso el guion en el teclado espanol o ingles
                case "-":
                case "–":
                case"+":
                    if(stack.isEmpty())
                        stack.push(t);
                    else{
                        if(stack.top().equals("+")||stack.top().equals("-")){
                            res+=(stack.pop()+" ");
                            stack.push(t);
                        }else if(stack.top().equals("*")||stack.top().equals("/")||stack.top().equals("^")){
                           while (!stack.isEmpty()){
                               res+=(stack.pop()+" ");
                           }
                           stack.push(t);
                        }else {
                            stack.push(t);
                        }
                    }
                    break;
                case "*":
                case "/":
                    if(stack.isEmpty())
                        stack.push(t);
                    else{
                        if(stack.top().equals("*")||stack.top().equals("/")){
                            res+=(stack.pop()+" ");
                            stack.push(t);
                        }else if(stack.top().equals("^")){
                            while (!stack.isEmpty()){
                                res+=(stack.pop()+" ");
                            }
                            stack.push(t);
                        }else if(stack.top().equals("+")||stack.top().equals("-")){
                            stack.push(t);
                        }else {
                            stack.push(t);
                        }
                    }
                    break;
                case "^":
                    if(stack.isEmpty())
                        stack.push(t);
                    else{
                        if(stack.top().equals("^")){
                            res+=(stack.pop()+" ");
                            stack.push(t);
                        }else if(stack.top().equals("+")||stack.top().equals("-")||stack.top().equals("*")||stack.top().equals("/")){
                            stack.push(t);
                        }else {
                            stack.push(t);
                        }
                    }
                    break;
                case "(":
                    stack.push(t);
                    break;
                case ")":
                    while (!stack.top().equals("(")){
                        res+=(stack.pop()+" ");
                    }
                    stack.pop();
                    break;
                default:
                    res+=(t+" ");
                    break;
            }
        }
        while (!stack.isEmpty()){
            res+=(stack.pop()+" ");
        }
        return res.trim();
    }

    public static void main(String[] args) {
        EvaluarExpresion exp = new EvaluarExpresion("10 + 20 * ( 50 / 2 ) - 5.8");
        System.out.println(exp.expresionPostfijo());
        System.out.println(exp.evaluaExpresion());
    }
}
