package nju.edu.hostel.vo.output;

import nju.edu.hostel.model.City;
import nju.edu.hostel.model.Province;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by disinuo on 17/5/29.
 */
public class CityVO {
    private int id;
    private String value;
    public CityVO(City entity){
        this.id=entity.getId();
        this.value=entity.getValue();
    }
    public static List<CityVO> entityToVO(List<City> cities){
        List<CityVO> vos=new ArrayList<CityVO>();
        for(City c:cities) {
            vos.add(new CityVO(c));
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
