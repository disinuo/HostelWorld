package nju.edu.hostel.service;

import nju.edu.hostel.model.City;
import nju.edu.hostel.model.Province;

import java.util.List;

/**
 * Created by disinuo on 17/5/29.
 */
public interface OtherService {

    public List<Province> getAllProvince();
    public List<City> getCitiesByProvince(int provinceId);
}
