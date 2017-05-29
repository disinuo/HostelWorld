package nju.edu.hostel.dao;

import nju.edu.hostel.model.City;
import nju.edu.hostel.model.Province;
import nju.edu.hostel.util.ResultMessage;

import java.util.List;
import java.util.Map;

/**
 * Created by disinuo on 17/3/3.
 */
public interface RegionDao {
    public List<Province> getAllProvince();
    public List<City> getCitiesByProvince(int provinceId);

}
