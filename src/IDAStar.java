import java.util.*;

public class IDAStar {
    public static void IDAStar(Node start, String goal){
        Stack<Node> L = new Stack<>();
        HashMap<String, Node> H = new HashMap<>();
        int t = HeuristicFunctions.heuristicFunctions(start.get_board());
        while (t != Integer.MAX_VALUE) {
            int minF = Integer.MAX_VALUE;
            L.push(start);H.put(start.get_id(), start);
            while (!L.isEmpty()) {
                Node n = L.pop();
                if (n.isOut()) H.remove(n.get_id());
                else {
                    n.setOut(true);L.push(n);
                    while (n.has_next_child()) {
                        Node g = n.get_child(); BFS.counter++;
                        if (g.get_regularCost() > t) {
                            minF = Math.min(minF, g.get_regularCost());
                        }
                        else if (H.containsKey(g.get_id()) && g.isOut()){
                            // continue
                        }
                        else if (H.containsKey(g.get_id()) && !g.isOut()) {
                            Node gTag = H.get(g.get_id());
                            if (gTag.get_regularCost() > g.get_regularCost()) {
                                L.remove(gTag); H.remove(g.get_id());
                            }
                            else {} // continue
                        }
                        else if (g.get_id().equals(goal)) {
                            BFS.printPath(g);
                            return;
                        }
                        else {
                            L.push(g); H.put(g.get_id(), g);
                        }
                    }
                }
            }
            t = minF;
        }
    }

    public static void IDAStarWithOpen(Node start, Node goal){

    }
}
