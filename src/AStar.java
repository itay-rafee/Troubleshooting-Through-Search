import java.util.*;

public class AStar {
    public static void aStar(Node start, String goal){
        if (start.get_id().equals(goal)){
            System.out.println(start.getPath());
            return;
        }

        PriorityQueue<Node> L = new PriorityQueue<>();
        HashMap<String, Node> open = new HashMap<>();
        HashMap<String, Node> close = new HashMap<>();
        L.add(start);
        open.put(start.get_id(), start);

        while (!L.isEmpty()){
            if (!Ex1.withoutOpen){
                System.out.println("Open List:\n" + L);
            }

            Node n  = L.poll();
            open.remove(n.get_id());

            if (n.get_id().equals(goal)) {
                BFS.printPath(n);
                return;
            }

            close.put(n.get_id(), n);
            while (n.has_next_child()) {
                BFS.counter++;
                Node newN = n.get_child();
                if (!open.containsKey(newN.get_id()) && !close.containsKey(newN.get_id())) {
                    open.put(newN.get_id(), newN);
                    L.add(newN);
                }
                else if (L.contains(newN)){
                    Node checkNode = open.get(newN.get_id());
                    if (checkNode.get_cost() > newN.get_cost()) {
                        L.remove(checkNode);
                        L.add(newN);
                        open.put(newN.get_id(), newN);
                    }
                }
            }
        }
    }

    public static void aStarWithOpen(Node start, Node goal){

    }
}
