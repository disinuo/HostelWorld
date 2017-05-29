package nju.edu.hostel.dao.Impl;

import nju.edu.hostel.dao.BaseDao;
import nju.edu.hostel.dao.RegionDao;
import nju.edu.hostel.model.City;
import nju.edu.hostel.model.Province;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by disinuo on 17/5/29.
 */
@Repository
public class RegionDaoImpl implements RegionDao{
    @Autowired
    BaseDao baseDao;
    @Override
    public List<Province> getAllProvince() {
        return baseDao.getAll(Province.class);
    }

    @Override
    public List<City> getCitiesByProvince(int provinceId) {
        int upRange=provinceId+9999;
        String hql="FROM City WHERE id BETWEEN "+provinceId+
                " AND "+upRange;
        return baseDao.getByHql(City.class,hql);
    }
}
