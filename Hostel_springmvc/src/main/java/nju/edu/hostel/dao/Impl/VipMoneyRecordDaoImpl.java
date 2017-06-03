package nju.edu.hostel.dao.Impl;

import nju.edu.hostel.dao.BaseDao;
import nju.edu.hostel.dao.BookBillDao;
import nju.edu.hostel.dao.VipMoneyRecordDao;
import nju.edu.hostel.model.BookBill;
import nju.edu.hostel.model.VipMoneyRecord;
import nju.edu.hostel.util.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by disinuo on 17/3/3.
 */
@Repository
public class VipMoneyRecordDaoImpl implements VipMoneyRecordDao {
    @Autowired
    BaseDao baseDao;

    @Override
    public VipMoneyRecord get(int id) {
        return baseDao.getEntity(VipMoneyRecord.class,id);
    }

    @Override
    public VipMoneyRecord load(int id) {
        return baseDao.loadProxy(VipMoneyRecord.class,id);
    }

    @Override
    public List<VipMoneyRecord> getByVipId(int vipId) {
        String hql="SELECT record FROM VipMoneyRecord as record WHERE record.vipId="+vipId;
        return baseDao.getByHql(VipMoneyRecord.class,hql);
    }

    @Override
    public int add(VipMoneyRecord vipMoneyRecord) throws Exception {
        return baseDao.save(vipMoneyRecord);
    }
    @Override
    public ResultMessage record(int vipId, double money, long date, int type){
        VipMoneyRecord record=new VipMoneyRecord(vipId,date,money,type);
        return addNoId(record);
    }

    @Override
    public ResultMessage addNoId(VipMoneyRecord vipMoneyRecord){
        return baseDao.saveNoId(vipMoneyRecord);
    }
    @Override
    public ResultMessage update(VipMoneyRecord vipMoneyRecord) {
        return baseDao.update(vipMoneyRecord);
    }
}
