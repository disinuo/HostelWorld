package nju.edu.hostel.dao.Impl;
import nju.edu.hostel.dao.BaseDao;
import nju.edu.hostel.dao.HostelDao;
import nju.edu.hostel.dao.Hostel_tmpDao;
import nju.edu.hostel.model.Hostel;
import nju.edu.hostel.model.Hostel_tmp;
import nju.edu.hostel.util.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by disinuo on 17/3/3.
 */
@Repository
public class Hostel_tmpDaoImpl implements Hostel_tmpDao {
    @Autowired
    BaseDao baseDao;

    @Override
    public Hostel_tmp get(int id) {
        return baseDao.getEntity(Hostel_tmp.class,id);
    }

    @Override
    public Hostel_tmp load(int id) {
        return baseDao.loadProxy(Hostel_tmp.class,id);

    }
    public List<Hostel_tmp> getByRestrictEqual(String column,Object value){
        return baseDao.getByRestrictEqual(Hostel_tmp.class,column,value);
    }
    @Override
    public List<Hostel_tmp> getByRestrictEqual(Map<String, Object> map) {
        return baseDao.getByRestrictEqual(Hostel_tmp.class,map);
    }

    @Override
    public int add(Hostel_tmp Hostel_tmp) throws Exception {
        return baseDao.save(Hostel_tmp);
    }

    @Override
    public ResultMessage update(Hostel_tmp Hostel_tmp) {
        return baseDao.update(Hostel_tmp);
    }
}
