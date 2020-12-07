import java.util.LinkedList;
import java.util.List;

public class LinkedListExample {
    public static void main(String[] args) {
        List<Integer> li = new LinkedList<>();
        li.add(23);
       ((LinkedList<Integer>) li).addFirst(12);
        System.out.print(li);
        li.add(1,35);
        System.out.print(li);
    }
}
