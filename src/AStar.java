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

//    public static void aStar1(Node start, String goal) {
//        if (start.get_id().equals(goal)){
//            System.out.println(start.getPath());
//            return;
//        }
//        PriorityQueue<Node> L = new PriorityQueue<>();
//        L.add(start);
//        HashMap<String, Node> C = new HashMap<>();
//        C.put(start.get_id(),start);
//        while (!L.isEmpty()){
//            Node n  = L.poll();
//            for (int i = 0; i < s1.length; i++) {
//                if (s1[i].equals(n.get_id())){
//                    if (i == 15)
//                    System.out.println(i);
//                }
//            }
//            if (n.get_id().equals(goal)) {
//                BFS.printPath(n);
//                return;
//            }
//            while (n.has_next_child()) {
//                Node newN = n.get_child();
//                if (!C.containsKey(newN.get_id())) {
//                    C.put(newN.get_id(), newN);
//                    L.add(newN);
//                }
//                else {
//                    if (L.contains(newN)){
//                        Node checkNode = C.get(newN.get_id());
//                        if (checkNode.get_cost() > newN.get_cost()) {
//                            L.remove(checkNode);
//                            L.add(newN);
//                            C.put(newN.get_id(), newN);
//                        }
//                    }
//                }
//            }
//        }
//    }

    public static void aStarWithOpen(Node start, Node goal){

    }
}
