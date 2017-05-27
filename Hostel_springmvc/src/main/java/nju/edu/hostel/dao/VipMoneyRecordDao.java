package nju.edu.hostel.dao;
import nju.edu.hostel.model.VipMoneyRecord;
import nju.edu.hostel.util.ResultMessage;

import java.util.List;
import java.util.Map;

/**
 * Created by disinuo on 17/3/3.
 */
public interface VipMoneyRecordDao {
    public VipMoneyRecord get(int id);
    public VipMoneyRecord load(int id);
    public List<VipMoneyRecord> getByRestrictEqual(String column, Object value);
    public List<VipMoneyRecord> getByRestrictEqual(Map<String, Object> map);
    public List<VipMoneyRecord> getAll();
    public int add(VipMoneyRecord vipMoneyRecord)throws Exception;
    public ResultMessage addNoId(VipMoneyRecord vipMoneyRecord);
    public ResultMessage update(VipMoneyRecord vipMoneyRecord);




}
