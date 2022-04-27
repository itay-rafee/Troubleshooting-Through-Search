import java.util.HashSet;


public class DFID {
    private static int counter = 1;
    public static final String cutoff = "cutoff", fail = "fail";
    public static void DFID(Node start, String goal){
        for (int depth = 1; depth < Integer.MAX_VALUE; depth++) {
            HashSet<String> H = new HashSet<>();
            String result = limitedDFS(start, goal, depth, H);
            if (!result.equals(cutoff) && !result.equals(fail)){
                System.out.println(result);
                return;
            }
            start = new Node(null, start.get_board(), start.get_space(), new int[2], start.get_id(), 0);
        }
        System.out.println("Not good!!");
    }

    private static String limitedDFS(Node n, String goal, int limit, HashSet<String> H) {
        if (goal.equals(n.get_id()))
            return printPath(n);
        else if (limit == 0) return cutoff;
        else {
            H.add(n.get_id());
            boolean isCutoff = false;
            while (n.has_next_child()){
                Node g = n.get_child(); counter++;
                if (!H.contains(g.get_id())){
                    String result = limitedDFS(g,goal,limit-1,H);
                    if (result.equals(cutoff))isCutoff = true;
                    else if (!result.equals(fail))return result;
                }
            }
            H.remove(n.get_id());
            if (isCutoff) return cutoff;
            else return fail;
        }
    }

    private static String printPath(Node n) {
        StringBuilder s = new StringBuilder(n.getPath());
        System.out.println("Num: "+counter);
        System.out.println("Cost: "+n.get_cost());
        while (n.get_father().get_father() != null){
            n = n.get_father();
            s.insert(0, n.getPath() + "--");
        }
        return s.toString();
    }

    public static void DFIDWithOpen(Node start, Node goal){

    }
}
