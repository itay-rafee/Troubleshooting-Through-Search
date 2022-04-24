import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class BFS {
    static int counter = 1;
    public static void bfs(Node start, String goal){
        if (start.get_id().equals(goal)){
            System.out.println(start.getPath());
            return;
        }
        Queue<Node> L = new LinkedList<>();
        L.add(start);
        HashSet<String> C = new HashSet<>();
        C.add(start.get_id());
        while (!L.isEmpty()){
            Node n  = L.remove();
            while (n.has_next_child()){
                Node newN = n.get_child();counter++;
                if (!C.contains(newN.get_id())){
                    if (newN.get_id().equals(goal)){
                        printPath(newN);
                        return;
                    }
                    C.add(newN.get_id());
                    L.add(newN);
                }
            }
        }
    }

    public static void printPath(Node n) {
        StringBuilder s = new StringBuilder(n.getPath());
        System.out.println("Num: "+counter);
        if (Ex1.needHeuristicFun) System.out.println("Cost: "+n.get_regularCost());
        else System.out.println("Cost: "+n.get_cost());
        while (n.get_father().get_father() != null){
            n = n.get_father();
            s.insert(0, n.getPath() + "--");
        }
        System.out.println(s.toString());
    }

    public static void bfsWithOpen(Node start, Node goal){

    }
}
