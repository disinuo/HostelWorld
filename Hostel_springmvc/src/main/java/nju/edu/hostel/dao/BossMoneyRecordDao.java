package nju.edu.hostel.dao;
import nju.edu.hostel.model.BossMoneyRecord;
import nju.edu.hostel.util.ResultMessage;

import java.util.List;
import java.util.Map;

/**
 * Created by disinuo on 17/3/3.
 */
public interface BossMoneyRecordDao {
    public BossMoneyRecord get(int id);
    public BossMoneyRecord load(int id);
    public List<BossMoneyRecord> getByRestrictEqual(String column, Object value);
    public List<BossMoneyRecord> getByRestrictEqual(Map<String, Object> map);
    public List<BossMoneyRecord> getAll();
    public int add(BossMoneyRecord bossMoneyRecord)throws Exception;
    public ResultMessage addNoId(BossMoneyRecord bossMoneyRecord);
    public ResultMessage update(BossMoneyRecord bossMoneyRecord);

    public ResultMessage record(double money,long date,int moneyType);


}
