package nju.edu.hostel.dao.Impl;
import nju.edu.hostel.dao.BaseDao;
import nju.edu.hostel.dao.HostelDao;
import nju.edu.hostel.model.Hostel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

/**
 * Created by disinuo on 17/3/3.
 */
@Repository
public class HostelDaoImpl implements HostelDao{
    @Autowired
    BaseDao baseDao;
    public List<Hostel> get(){
        String[] columnName={"permitted"};
        Object[] values={true};
        return baseDao.findByColunms(Hostel.class,columnName,values);
    }
}
