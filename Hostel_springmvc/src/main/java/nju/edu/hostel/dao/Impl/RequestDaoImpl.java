package nju.edu.hostel.dao.Impl;

import nju.edu.hostel.dao.BaseDao;
import nju.edu.hostel.dao.RequestDao;
import nju.edu.hostel.model.RequestModify;
import nju.edu.hostel.model.RequestOpen;
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
    public RequestOpen getOpenRequest(int id) {
        return baseDao.getEntity(RequestOpen.class,id);
    }

    @Override
    public RequestOpen loadOpenRequest(int id) {
        return baseDao.loadProxy(RequestOpen.class,id);
    }

    @Override
    public List<RequestOpen> getOpenRequestByRestrictEqual(String column, Object value) {

        return baseDao.getByRestrictEqual(RequestOpen.class,column,value);
    }

    @Override
    public List<RequestOpen> getOpenRequestByRestrictEqual(Map<String, Object> map) {

        return baseDao.getByRestrictEqual(RequestOpen.class,map);

    }
    @Override
    public int addOpenRequest(RequestOpen requestOpen) throws Exception {
        return baseDao.save(requestOpen);
    }

    @Override
    public ResultMessage updateOpenRequest(RequestOpen requestOpen) {
        return baseDao.update(requestOpen);
    }
//    ==========================
@Override
public RequestModify getModifyRequest(int id) {
    return baseDao.getEntity(RequestModify.class,id);
}

    @Override
    public RequestModify loadModifyRequest(int id) {
        return baseDao.loadProxy(RequestModify.class,id);
    }

    @Override
    public List<RequestModify> getModifyRequestByRestrictEqual(String column, Object value) {

        return baseDao.getByRestrictEqual(RequestModify.class,column,value);
    }

    @Override
    public List<RequestModify> getModifyRequestByRestrictEqual(Map<String, Object> map) {

        return baseDao.getByRestrictEqual(RequestModify.class,map);

    }
    @Override
    public int addModifyRequest(RequestModify requestModify) throws Exception {
        return baseDao.save(requestModify);
    }

    @Override
    public ResultMessage updateModifyRequest(RequestModify requestModify) {
        return baseDao.update(requestModify);
    }
    
    
    
    
    
}