package nju.edu.hostel.dao.Impl;
import nju.edu.hostel.dao.BaseDao;
import nju.edu.hostel.dao.HostelDao;
import nju.edu.hostel.model.Hostel;
import nju.edu.hostel.util.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by disinuo on 17/3/3.
 */
@Repository
public class HostelDaoImpl implements HostelDao{
    @Autowired
    BaseDao baseDao;

    @Override
    public Hostel get(int id) {
        return baseDao.getEntity(Hostel.class,id);
    }

    @Override
    public Hostel load(int id) {
        return baseDao.loadProxy(Hostel.class,id);

    }

    @Override
    public List<Hostel> getAllPermitted() {
        String hql="SELECT hostel FROM Hostel as hostel WHERE hostel.permitted=true ORDER BY hostel.numOfPeople DESC";
        return baseDao.getByHql(Hostel.class,hql);
    }


    @Override
    public int add(Hostel hostel) throws Exception {
        return baseDao.save(hostel);
    }

    @Override
    public ResultMessage update(Hostel hostel) {
        return baseDao.update(hostel);
    }
}
