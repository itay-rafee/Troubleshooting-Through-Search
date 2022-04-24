import java.util.*;

public class AStar {
    public static void aStar(Node start, String goal){
        if (start.get_id().equals(goal)){
            System.out.println(start.getPath());
            return;
        }
        PriorityQueue<Node> L = new PriorityQueue<>();
        L.add(start);
        HashMap<String, Node> C = new HashMap<>();
        while (!L.isEmpty()){
            Node n  = L.poll();
            if (n.get_id().equals(goal)) {
                BFS.printPath(n);
                return;
            }
            C.put(n.get_id(), n);
            while (n.has_next_child()) {
                BFS.counter++;
                Node newN = n.get_child();
                if (!C.containsKey(newN.get_id()) && !L.contains(newN)) {
                    C.put(newN.get_id(), newN);
                    L.add(newN);
                }
                else if (L.contains(newN)){
                    Node checkNode = C.get(newN.get_id());
                    if (checkNode.get_cost() > newN.get_cost()) {
                        L.remove(checkNode);
                        L.add(newN);
                        C.put(newN.get_id(), newN);
                    }
//                    Stack<Node> stack = new Stack<>();
//                    while (!L.isEmpty()) {
//                        Node checkNode = L.poll();
//                        if (checkNode.equals(newN)){
//                            if (newN.get_cost() > checkNode.get_cost()){
//                                stack.push(checkNode);
//                            }
//                            else stack.push(newN);
//                            break;
//                        }
//                        stack.push(checkNode);
//                    }
//                    while (!stack.isEmpty()) {
//                        L.add(stack.pop());
//                    }
                }
            }
        }
    }

    public static void aStarWithOpen(Node start, Node goal){

    }
}
