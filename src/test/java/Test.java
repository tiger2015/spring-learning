import java.util.*;

/**
 * 重载的选择是静态，覆盖的选择是动态
 */
public class Test {

    public static String classify(Set<?> s){
        return "Set";
    }

    public static String classify(List<?> l){
        return "List";
    }

    public static String classify(Collection<?> l){
        return "Collection";
    }


    public static void main(String[] args) {
        Collection<?>[] collections ={
                new HashSet<String>(),
                new ArrayList<String>(),
                new HashMap<String,String>().values()
        };

        for (Collection<?> c : collections){
            System.out.println(classify(c));
        }

    }

}
