import java.util.Arrays;

public class Algo {
    static int counter = 0;
    public static String path(Node n) {
        StringBuilder s = new StringBuilder(n.getPath());
        s.append("\nNum: ").append(counter).append("\n");
        if (Ex1.needHeuristicFun) s.append("Cost: ").append(n.get_regularCost()).append("\n");
        else s.append("Cost: ").append(n.get_cost()).append("\n");
        if (n.get_father() == null)return s.toString();
        int i1 = 0;
        while (n.get_father().get_father() != null){
            n = n.get_father();
            //// for real ////
            s.insert(0, n.getPath() + "--");
            //// for test ////
//            StringBuilder s1 = new StringBuilder();
//            for (int i = 0; i < n.get_board().length; i++) {
//                s1.append(Arrays.toString(n.get_board()[i])).append("\n");
//            }
//            s1.append(n.get_regularCost()).append("\n");
//            s1.append((i1++)).append("\n");
//            s.insert(0, s1 + "\n\n");
        }
        return s.toString();
    }


    public static String getNoPath(){
        return "no path" + "\nNum: " + counter + "\n" +
                "Cost: inf" + "\n";
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
