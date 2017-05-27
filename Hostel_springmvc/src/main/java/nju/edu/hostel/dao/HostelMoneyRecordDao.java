package nju.edu.hostel.dao;
import nju.edu.hostel.model.HostelMoneyRecord;
import nju.edu.hostel.util.ResultMessage;

import java.util.List;
import java.util.Map;

/**
 * Created by disinuo on 17/3/3.
 */
public interface HostelMoneyRecordDao {
    public HostelMoneyRecord get(int id);
    public HostelMoneyRecord load(int id);
    public List<HostelMoneyRecord> getByRestrictEqual(String column, Object value);
    public List<HostelMoneyRecord> getByRestrictEqual(Map<String, Object> map);
    public List<HostelMoneyRecord> getAll();
    public int add(HostelMoneyRecord hostelMoneyRecord)throws Exception;
    public ResultMessage addNoId(HostelMoneyRecord hostelMoneyRecord);
    public ResultMessage record(int hostelId, double money, long date, int type);

    public ResultMessage update(HostelMoneyRecord hostelMoneyRecord);




}
