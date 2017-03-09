package nju.edu.hostel.dao;

import nju.edu.hostel.model.Hostel;
import nju.edu.hostel.util.ResultMessage;

import java.util.List;
import java.util.Map;

/**
 * Created by disinuo on 17/3/3.
 */
public interface HostelDao {
    public List<Hostel> getByRestrictEqual(String column,Object value);
    public Hostel get(int id);
    public Hostel load(int id);
    public List<Hostel> getByRestrictEqual(Map<String,Object> map);

    public int add(Hostel hostel)throws Exception;
    public ResultMessage update(Hostel hostel);
}
