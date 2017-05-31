package nju.edu.hostel.vo.output;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by disinuo on 17/5/31.
 */
public class DataVO {
    String key;
    Object value;

    public static List<DataVO> mapToVO(Map map){
        List<DataVO> list=new ArrayList<DataVO>();

        for(Object key:map.keySet()){
            list.add(new DataVO(key.toString(),map.get(key)));
        }
        return list;
    }

    public DataVO(String key,Object value){
        this.key=key;
        this.value=value;
    }
    public DataVO(){}
    public String getKey() {
        return key;
    }

    public Object getValue() {
        return value;
    }
}
