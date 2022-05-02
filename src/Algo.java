import java.util.Arrays;

/**
 * The algorithm extend from this class
 */
public class Algo {
    static int counter = 1;

    /**
     * @param n the lest node of the path
     * @return the path we get to n
     */
    public static String path(Node n) {
        StringBuilder s = new StringBuilder();
        s.append("Num: ").append(counter).append("\n");
        if (Ex1.needHeuristicFun) s.append("Cost: ").append(n.get_regularCost()).append("\n");
        else s.append("Cost: ").append(n.get_cost()).append("\n");
        if (n.get_father() == null)return s.toString();
        s.insert(0, n.getPath() + "\n");
        while (n.get_father().get_father() != null){
            n = n.get_father();
            s.insert(0, n.getPath() + "--");
        }
        return s.toString();
    }


    /**
     * @return the answer when we get no path
     */
    public static String getNoPath(){
        return "no path" + "\nNum: " + counter + "\n" +
                "Cost: inf" + "\n";
    }
}
