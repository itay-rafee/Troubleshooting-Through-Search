import java.util.*;

public class IDAStar extends Algo {
    public static String finedPath(Node start, String goal){
        if (start.get_id().equals(goal)){
            return path(start);
        }

        Stack<Node> L = new Stack<>();
        HashMap<String, Node> H = new HashMap<>();
        int t = HeuristicFunctions.heuristicFunctions(start.get_board());

        while (t != Integer.MAX_VALUE) {
            int minF = Integer.MAX_VALUE;
            L.push(start);
            H.put(start.get_id(), start);

            while (!L.isEmpty()) {
                if (!Ex1.withoutOpen){
                    System.out.println("Open List:(size "+L.size()+")\n" + L + "\n");
                }

                Node n = L.pop();
                if (n.isOut()) {
                    H.remove(n.get_id());
                }
                else {
                    n.setOut(true);
                    L.push(n);

                    while (n.has_next_child()) {
                        Node g = n.get_child(); counter++;
                        if (g.get_cost() > t) {
                            minF = Math.min(minF, g.get_cost());
                            continue;
                        }
                        else if (H.containsKey(g.get_id()) && H.get(g.get_id()).isOut()){
                            continue;
                        }
                        else if (H.containsKey(g.get_id()) && !H.get(g.get_id()).isOut()) {
                            Node gTag = H.get(g.get_id());
                            if (gTag.get_cost() > g.get_cost()) {
                                L.remove(gTag); H.remove(g.get_id());
                            }
                            else {
                                continue;
                            }
                        }
                        else if (g.get_id().equals(goal)) {
                            return path(g);
                        }
                        L.push(g);
                        H.put(g.get_id(), g);
                    }
                }
            }
            t = minF;
            start = new Node(null, start.get_board(), start.get_space(), new int[2], start.get_id(), 0);
        }
        return getNoPath();
    }
}
