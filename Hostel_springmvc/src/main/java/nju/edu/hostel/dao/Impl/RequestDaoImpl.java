package nju.edu.hostel.dao.Impl;

import com.sun.org.apache.regexp.internal.RE;
import nju.edu.hostel.dao.BaseDao;
import nju.edu.hostel.dao.RequestDao;
import nju.edu.hostel.model.RequestModify;
import nju.edu.hostel.model.RequestOpen;
import nju.edu.hostel.util.RequestState;
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
    String baseHql_open="SELECT request FROM RequestOpen as request WHERE ";
    String baseHql_modify="SELECT request FROM RequestOpen as request WHERE ";

    @Override
    public RequestOpen getOpenRequest(int id) {
        return baseDao.getEntity(RequestOpen.class,id);
    }

    @Override
    public List<RequestOpen> getOpenRequestByHostel(int hostelId) {
        String hql=baseHql_open+"request.hostel.id="+hostelId;
        return baseDao.getByHql(RequestOpen.class,hql);
    }

    @Override
    public List<RequestOpen> getUncheckedOpenRequests() {
        String hql=baseHql_open+"request.state='"+ RequestState.UNCHECKED.toString()+"'";
        return baseDao.getByHql(RequestOpen.class,hql);
    }

    @Override
    public RequestOpen loadOpenRequest(int id) {
        return baseDao.loadProxy(RequestOpen.class,id);
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
    public List<RequestModify> getUncheckedModifyRequests() {
        String hql=baseHql_modify+"request.state="+ RequestState.UNCHECKED.toString();
        return baseDao.getByHql(RequestModify.class,hql);
    }

    @Override
    public RequestModify loadModifyRequest(int id) {
        return baseDao.loadProxy(RequestModify.class,id);
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