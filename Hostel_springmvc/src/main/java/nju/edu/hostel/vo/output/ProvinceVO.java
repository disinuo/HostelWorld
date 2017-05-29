package nju.edu.hostel.vo.output;

import nju.edu.hostel.model.Province;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by disinuo on 17/5/29.
 */
public class ProvinceVO {
    private int id;
    private String value;
    public ProvinceVO(Province entity){
        this.id=entity.getId();
        this.value=entity.getValue();
    }
    public static List<ProvinceVO> entityToVO(List<Province> provinces){
        List<ProvinceVO> vos=new ArrayList<ProvinceVO>();
        for(Province p:provinces) {
            vos.add(new ProvinceVO(p));
        }
        return vos;
    }

    public int getId() {
        return id;
    }

    public String getValue() {
        return value;
    }
}
