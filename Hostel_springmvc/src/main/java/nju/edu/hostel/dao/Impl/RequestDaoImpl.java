package nju.edu.hostel.dao.Impl;

import nju.edu.hostel.dao.BaseDao;
import nju.edu.hostel.dao.RequestDao;
import nju.edu.hostel.model.Request;
import nju.edu.hostel.util.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by disinuo on 17/3/11.
 */
@Repository
public class RequestDaoImpl implements RequestDao {
    @Autowired
    BaseDao baseDao;

    @Override
    public Request get(int id) {
        return baseDao.getEntity(Request.class,id);
    }

    @Override
    public Request load(int id) {
        return baseDao.loadProxy(Request.class,id);
    }

    @Override
    public List<Request> getByRestrictEqual(String column, Object value) {

        return baseDao.getByRestrictEqual(Request.class,column,value);
    }

    @Override
    public List<Request> getByRestrictEqual(Map<String, Object> map) {

        return baseDao.getByRestrictEqual(Request.class,map);

    }

    @Override
    public int add(Request request) throws Exception {
        return baseDao.save(request);
    }

    @Override
    public ResultMessage update(Request request) {
        return baseDao.update(request);
    }
}