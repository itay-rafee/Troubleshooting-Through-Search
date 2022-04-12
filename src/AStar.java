import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class AStar {
    public static void aStar(Node start, String goal){

    }

    public static void UCS(Node start, String goal) {
        if (start.get_id().equals(goal)){
            System.out.println(start.getPath());
            return;
        }
        PriorityQueue<Node> L = new PriorityQueue<>();
        L.add(start);
        HashSet<String> C = new HashSet<>();
        C.add(start.get_id());
        while (!L.isEmpty()){
            Node n  = L.poll();
            if (n.get_id().equals(goal)) {
                BFS.printPath(n);
                return;
            }
            while (n.has_next_child()) {
                Node newN = n.get_child();
                if (!C.contains(newN.get_id())) {
                    C.add(newN.get_id());
                    L.add(newN);
                }
                // maybe should by here else
            }
        }
    }
    public static void aStarWithOpen(Node start, Node goal){

    }
}
