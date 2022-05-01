import java.util.HashSet;


public class DFID extends Algo {
    public static final String cutoff = "cutoff", fail = "fail";
    public static String finedPath(Node start, String goal){
        for (int depth = 1; depth < Integer.MAX_VALUE; depth++) {
            System.out.println(depth);
            HashSet<String> H = new HashSet<>();
            String result = limitedDFS(start, goal, depth, H);
            if (!result.equals(cutoff) && !result.equals(fail)){
                return result;
            }
            start = new Node(null, start.get_board(), start.get_space(), new int[2], start.get_id(), 0);
        }
        return getNoPath();
    }

    private static String limitedDFS(Node n, String goal, int limit, HashSet<String> H) {
        if (goal.equals(n.get_id()))
            return path(n);
        else if (limit == 0) return cutoff;
        else {
            if (!Ex1.withoutOpen){
                System.out.println("Open List:(size "+H.size()+")\n" + H);
            }
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
}
