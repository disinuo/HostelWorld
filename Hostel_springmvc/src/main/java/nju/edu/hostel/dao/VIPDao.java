package nju.edu.hostel.dao;
import nju.edu.hostel.model.Vip;
import nju.edu.hostel.util.ResultMessage;

import java.util.List;
import java.util.Map;

/**
 * Created by disinuo on 17/3/3.
 */
public interface VIPDao {
    public Vip get(int id);
    public Vip load(int id);
    public List<Vip> getByRestrictEqual(String column, Object value);
    public List<Vip> getByRestrictEqual(Map<String,Object> map);

    public int add(Vip vip)throws Exception;
    public ResultMessage update(Vip vip);




}
