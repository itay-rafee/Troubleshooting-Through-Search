import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class BFS {
    public static void bfs(Node start, String goal){
        Queue<Node> L = new LinkedList<>();
        L.add(start);
        HashSet<String> C = new HashSet<>();
        while (!L.isEmpty()){
            Node n  = L.remove();
            C.add(n.get_id());
            while (n.has_next_child()){
                Node newN = n.get_child();
                if (!C.contains(newN.get_id())){
                    if (newN.get_id().equals(goal)){
                        printPath(newN);
                    }
                }
            }
        }
    }

    private static void printPath(Node n) {

    }

    public static void bfsWithOpen(Node start, Node goal){

    }
}
