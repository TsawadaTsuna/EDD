//Kevin Daniel Contreras Hernandez A01635597
//FAVOR DE NO BORRAR LOS COMENTARIOS
//NI MOVERLOS DE POSICI�N
//Completa la firma de la clase
public class ABBSol202011<E extends Comparable<E>> extends ABBAbstract<E>{
	public ABBSol202011() {
		//Llama al constructor padre
		super();
	}
	
	//Implementar la inserci�n en el ABB. Puedes suponer que el valor
	//que se va a insertar no

	public void insertar(E valor) {
		System.out.println("Insertando "+valor);
		if(this.size==0) {
			this.valores[0] = valor;
		}else{
			int current=0,
				pre=0;
			while(this.valores[current]!=null) {
				pre=current;
				if (valor.compareTo(this.valores[current])>0){
					current=current*2+2;
				}else{
					current=current*2+1;
				}
				if(current>this.valores.length-1){
					this.expansion();
				}
			}
			if(valor.compareTo(this.valores[pre])>0){
				this.valores[pre*2+2]=valor;
			}else{
				this.valores[pre*2+1]=valor;
			}
		}
		this.size++;
		//Prueba este m�todo en el main insertando los valores
		//{3969,904,2692,3474,393,2838,2006,1815,2973,4017,2760,4911,2271,3667,2726,3064,4889,4634,2386,4637,926,1143,3015,1125,100,2479,2288,2451,1577,3866,1370,3388,1375,1497,145,1947,1914,871,2645,2132,2584,3569,673,4452,496,3211,4307,4688,4920,2303};

	}
	
	//Aqu� implementa la expansion del arreglo.
	//Se llamar� s�lo cuando la posicion donde se debe insertar un valor no existe
	protected void expansion() {
		E[] tmp =(E[])new Comparable[this.valores.length*2+1];
		for (int i=0;i<this.valores.length;i++){
			tmp[i]=this.valores[i];
		}
		this.valores=tmp;
	}



	//Funcion de preparaci�n. Despu�s de escribir todo el recorrido debe haber un salto de linea
	public void inorden() {
		this.inorden(0);
		System.out.println("");
	}

	//Funci�n recursiva que imprime el recorrido inorden del arbol separando cada elemento por una coma
	//y sin espacios en blanco
	protected void inorden(int pos) {
		if(this.valores[pos]!=null){
			if(2*pos+1<this.valores.length)
				this.inorden(2*pos+1);
			System.out.print(this.valores[pos]+",");
			if(2*pos+1<this.valores.length)
				this.inorden(2*pos+2);
		}
	}

	public static void main(String[] args) {
		//Haz una instancia de esta clase en la cual el gen�rico sea un Integer
		ABBSol202011<Integer> abb = new ABBSol202011<>();
		//Inicializa un arreglo con los valores a insertar en el ABB.
		//(Descomentar�a la siguiente l�nea y completala)
		Integer[] enteros= {3969,904,2692,3474,393,2838,2006,1815,2973,4017,2760,4911,2271,3667,2726,3064,4889,4634,2386,4637,926,1143,3015,1125,100,2479,2288,2451,1577,3866,1370,3388,1375,1497,145,1947,1914,871,2645,2132,2584,3569,673,4452,496,3211,4307,4688,4920,2303};
		
		
		//Haz un ciclo para ir insertando los valores del arreglo en el ABB
		for(Integer e:enteros){
			abb.insertar(e);
		}
	}
}