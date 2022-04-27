import java.util.Arrays;
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
        HashSet<String> open = new HashSet<>();
        HashSet<String> close = new HashSet<>();
        open.add(start.get_id());
        while (!L.isEmpty()){
            if (!Ex1.withoutOpen){
                System.out.println("Open List:\n" + L);
            }

            Node n  = L.remove();
            open.remove(n.get_id());
            close.add(n.get_id());

            while (n.has_next_child()){
                Node newN = n.get_child();counter++;
                if (!open.contains(newN.get_id()) && !close.contains(newN.get_id())){
                    if (newN.get_id().equals(goal)){
                        printPath(newN);
                        return;
                    }
                    open.add(newN.get_id());
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
        if (n.get_father() == null)return;
        int i1 = 16;
        while (n.get_father().get_father() != null){
            n = n.get_father();
            StringBuilder s1 = new StringBuilder();
            for (int i = 0; i < n.get_board().length; i++) {
                s1.append(Arrays.toString(n.get_board()[i])).append("\n");
            }
            s1.append(n.get_regularCost()).append("\n");
            s1.append((i1--)).append("\n");
            s.insert(0, s1 + "\n\n");
        }
        System.out.println(s.toString());
    }

    public static void bfsWithOpen(Node start, Node goal){

    }

    public static String getPath(Node n) {
        StringBuilder s = new StringBuilder(n.getPath());
        while (n.get_father().get_father() != null){
            n = n.get_father();
            s.insert(0, n.getPath() + "--");
        }
        return s.toString();
    }
}

//////////  check /////////////
//        while (n.get_father().get_father() != null){
//            n = n.get_father();
//            StringBuilder s1 = new StringBuilder();
////            for (int i = 0; i < n.get_board().length; i++) {
////                s1.append(Arrays.toString(n.get_board()[i])).append("\n");
////            }
////            s1.append(n.get_regularCost()).append("\n");
//            s1.append("\"").append(n.get_id()).append("\",\n");
//            s.insert(0, s1);
//        }