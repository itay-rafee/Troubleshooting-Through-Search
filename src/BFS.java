import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class BFS extends Algo{
    public static String finedPath(Node start, String goal){
        if (start.get_id().equals(goal)){
            return path(start);
        }
        Queue<Node> L = new LinkedList<>();
        L.add(start);
        HashSet<String> open = new HashSet<>();
        HashSet<String> close = new HashSet<>();
        open.add(start.get_id());
        while (!L.isEmpty()){
            if (!Ex1.withoutOpen){
                System.out.println("Open List:(size "+L.size()+")\n" + L + "\n");
            }

            Node n  = L.remove();
            open.remove(n.get_id());
            close.add(n.get_id());

            while (n.has_next_child()){
                Node newN = n.get_child();counter++;
                if (!open.contains(newN.get_id()) && !close.contains(newN.get_id())){
                    if (newN.get_id().equals(goal)){
                        return path(newN);
                    }
                    open.add(newN.get_id());
                    L.add(newN);
                }
            }
        }
        return getNoPath();
    }
}
