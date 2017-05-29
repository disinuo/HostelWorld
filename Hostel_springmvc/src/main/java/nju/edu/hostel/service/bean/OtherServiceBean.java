package nju.edu.hostel.service.bean;

import nju.edu.hostel.dao.RegionDao;
import nju.edu.hostel.model.City;
import nju.edu.hostel.model.Province;
import nju.edu.hostel.service.OtherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by disinuo on 17/5/29.
 */
@Transactional
@Service
public class OtherServiceBean implements OtherService{
    @Override
    public List<Province> getAllProvince() {
        return regionDao.getAllProvince();

    }

    @Override
    public List<City> getCitiesByProvince(int provinceId) {
        return regionDao.getCitiesByProvince(provinceId);
    }

    @Autowired
    RegionDao regionDao;
}
