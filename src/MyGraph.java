import java.util.ArrayList;

public class MyGraph <E> {
    private ArrayList<ArrayList<E>> grafo;

    public MyGraph(){
        grafo=new ArrayList<>();
        grafo.add(new ArrayList<>());
    }
}
