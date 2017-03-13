package nju.edu.hostel.dao;

import nju.edu.hostel.model.Hostel;
import nju.edu.hostel.model.Hostel_tmp;
import nju.edu.hostel.util.ResultMessage;

import java.util.List;
import java.util.Map;

/**
 * Created by disinuo on 17/3/3.
 */
public interface Hostel_tmpDao {
    public List<Hostel_tmp> getByRestrictEqual(String column, Object value);
    public Hostel_tmp get(int id);
    public Hostel_tmp load(int id);
    public List<Hostel_tmp> getByRestrictEqual(Map<String, Object> map);

    public int add(Hostel_tmp Hostel_tmp)throws Exception;
    public ResultMessage update(Hostel_tmp Hostel_tmp);
}
