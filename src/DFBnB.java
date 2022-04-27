import java.util.*;

public class DFBnB {
    public static void DFBnB(Node start, String goal) {
        Stack<Node> L = new Stack<>();
        HashMap<String, Node> H = new HashMap<>();
        L.push(start);
        H.put(start.get_id(), start);
        String result = null;
        int t = Integer.MAX_VALUE;
        while (!L.isEmpty()) {
            Node n = L.pop();
            if (n.isOut()) {
                H.remove(n.get_id());
            } else {
                n.setOut(true);
                L.push(n);
                ArrayList<Node> N = new ArrayList<>();
                while (n.has_next_child()) {
                    N.add(n.get_child());
                }
                Collections.sort(N);
                ArrayList<Node> copyN = new ArrayList<>();
                for (Node g : N) {
                    if (g.get_regularCost() >= t)break;
                    else if (H.containsKey(g.get_id()) && H.get(g.get_id()).isOut()) {
                    }
                    else if (H.containsKey(g.get_id()) && !H.get(g.get_id()).isOut()) {
                        if (!(H.get(g.get_id()).get_regularCost() <= g.get_regularCost())){
                            copyN.add(g);
                            Node gTag = H.get(g.get_id());
                            L.remove(gTag);
                            H.remove(g.get_id());
                        }
                    }
                    else if (g.get_id().equals(goal)){
                        t = g.get_regularCost();
                        result = BFS.getPath(g);
                        break;
                    }
                    else {copyN.add(g);}
                }
                for (int i = copyN.size(); i > 0 ; i--) {
                    Node node = copyN.get(i-1);
                    L.push(node);
                    H.put(node.get_id(), node);
                }
            }
        }
        System.out.println(result);
    }

    public static void DFBnBWithOpen(Node start, Node goal) {

    }
}
