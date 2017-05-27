package nju.edu.hostel.dao.Impl;

import nju.edu.hostel.dao.BaseDao;
import nju.edu.hostel.dao.BossMoneyRecordDao;
import nju.edu.hostel.model.BossMoneyRecord;
import nju.edu.hostel.util.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

import static nju.edu.hostel.util.Constants.MANAGER_ID;

/**
 * Created by disinuo on 17/3/3.
 */
@Repository
public class BossMoneyRecordDaoImpl implements BossMoneyRecordDao {
    @Autowired
    BaseDao baseDao;

    @Override
    public BossMoneyRecord get(int id) {
        return baseDao.getEntity(BossMoneyRecord.class,id);
    }

    @Override
    public BossMoneyRecord load(int id) {
        return baseDao.loadProxy(BossMoneyRecord.class,id);
    }

    @Override
    public List<BossMoneyRecord> getByRestrictEqual(String column, Object value) {
        return baseDao.getByRestrictEqual(BossMoneyRecord.class,column,value);
    }

    @Override
    public List<BossMoneyRecord> getByRestrictEqual(Map<String, Object> map) {
        return baseDao.getByRestrictEqual(BossMoneyRecord.class,map);
    }

    @Override
    public List<BossMoneyRecord> getAll() {
        return baseDao.getAll(BossMoneyRecord.class);
    }

    @Override
    public int add(BossMoneyRecord bossMoneyRecord) throws Exception {
        return baseDao.save(bossMoneyRecord);
    }
    @Override
    public ResultMessage addNoId(BossMoneyRecord bossMoneyRecord){
        return baseDao.saveNoId(bossMoneyRecord);
    }

    @Override
    public ResultMessage update(BossMoneyRecord bossMoneyRecord) {
        return baseDao.update(bossMoneyRecord);
    }
    @Override
    public ResultMessage record(double money,long date,int moneyType){
        BossMoneyRecord bossMoneyRecord=new BossMoneyRecord(
                MANAGER_ID,
                money,
                date,
                moneyType
        );
        return addNoId(bossMoneyRecord);
    }
}
