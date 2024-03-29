import java.util.*;

public class DFBnB extends Algo {

    public static String finedPath(Node start, String goal) {
        if (start.get_id().equals(goal)) {
            return path(start);
        }
        Stack<Node> L = new Stack<>();
        HashMap<String, Node> H = new HashMap<>();
        L.push(start);
        H.put(start.get_id(), start);

        String result = null;
        int t = Integer.MAX_VALUE;

        while (!L.isEmpty()) {
            if (!Ex1.withoutOpen) {
                System.out.println("Open List:(size " + L.size() + ")\n" + L + "\n");
            }

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
                counter += N.size();
                ArrayList<Node> copyN = new ArrayList<>();
                for (Node g : N) {
                    if (g.get_cost() >= t) {
                        break;
                    } else if (H.containsKey(g.get_id()) && H.get(g.get_id()).isOut()) {
                        continue;
                    } else if (H.containsKey(g.get_id()) && !H.get(g.get_id()).isOut()) {
                        if (!(H.get(g.get_id()).get_cost() <= g.get_cost())) {
                            Node gTag = H.get(g.get_id());
                            copyN.add(g);
                            L.remove(gTag);
                            H.remove(g.get_id());
                        } else {
                            continue;
                        }
                    } else if (g.get_id().equals(goal)) {
                        t = g.get_cost();
                        result = path(g);
                        break;
                    }
                    copyN.add(g);
                }
                for (int i = copyN.size(); i > 0; i--) {
                    Node node = copyN.get(i - 1);
                    L.push(node);
                    H.put(node.get_id(), node);
                }
            }
        }
        if (result == null) result = getNoPath();
        return result;
    }
}
