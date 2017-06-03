package nju.edu.hostel.dao.Impl;

import nju.edu.hostel.dao.BaseDao;
import nju.edu.hostel.dao.HostelMoneyRecordDao;
import nju.edu.hostel.model.HostelMoneyRecord;
import nju.edu.hostel.util.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by disinuo on 17/3/3.
 */
@Repository
public class HostelMoneyRecordDaoImpl implements HostelMoneyRecordDao {
    @Autowired
    BaseDao baseDao;

    @Override
    public HostelMoneyRecord get(int id) {
        return baseDao.getEntity(HostelMoneyRecord.class,id);
    }

    @Override
    public HostelMoneyRecord load(int id) {
        return baseDao.loadProxy(HostelMoneyRecord.class,id);
    }

    @Override
    public List<HostelMoneyRecord> getByHostel(int hostelId) {
        String hql="SELECT record FROM HostelMoneyRecord as record WHERE record.hostelId="+hostelId
                +" ORDER BY record.id DESC" ;
        return baseDao.getByHql(HostelMoneyRecord.class,hql);
    }


    @Override
    public int add(HostelMoneyRecord hostelMoneyRecord) throws Exception {
        return baseDao.save(hostelMoneyRecord);
    }
    @Override
    public ResultMessage addNoId(HostelMoneyRecord hostelMoneyRecord){
        return baseDao.saveNoId(hostelMoneyRecord);
    }
    @Override
    public ResultMessage record(int hostelId, double money, long date, int type){
        HostelMoneyRecord hostelMoneyRecord=new HostelMoneyRecord(
                hostelId,
                money,
                date,
                type);
        return addNoId(hostelMoneyRecord);
    }
    @Override
    public ResultMessage update(HostelMoneyRecord hostelMoneyRecord) {
        return baseDao.update(hostelMoneyRecord);
    }
}
