package nju.edu.hostel.dao;

import nju.edu.hostel.model.Request;
import nju.edu.hostel.util.ResultMessage;

import java.util.List;
import java.util.Map;

/**
 * Created by disinuo on 17/3/11.
 */
public interface RequestDao {
    public Request get(int id);
    public Request load(int id);
    public List<Request> getByRestrictEqual(String column, Object value);
    public List<Request> getByRestrictEqual(Map<String,Object> map);

    public int add(Request request)throws Exception;
    public ResultMessage update(Request request);

}
