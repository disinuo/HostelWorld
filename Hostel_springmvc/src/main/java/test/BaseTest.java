package test;
/**
 * Created by disinuo on 17/3/9.
 */



import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by disinuo on 17/3/5.
 */

public class BaseTest {
    public static void main(String[]args){
        Map<String,Double> map= new LinkedHashMap<>();
        map.put("a",33.0);
        map.put("b",33.0);
        map.put("3",33.0);
        map.put("4",33.0);
        map.put("1",33.0);

        System.out.println(map);
    }


}