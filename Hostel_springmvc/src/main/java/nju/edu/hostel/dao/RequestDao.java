package nju.edu.hostel.dao;

import nju.edu.hostel.model.RequestModify;
import nju.edu.hostel.model.RequestOpen;
import nju.edu.hostel.util.ResultMessage;

import java.util.List;
import java.util.Map;

/**
 * Created by disinuo on 17/3/11.
 */
public interface RequestDao {
    public RequestOpen getOpenRequest(int id);
    public RequestOpen loadOpenRequest(int id);
    public List<RequestOpen> getOpenRequestByRestrictEqual(String column, Object value);
    public List<RequestOpen> getOpenRequestByRestrictEqual(Map<String,Object> map);
    public int addOpenRequest(RequestOpen requestOpen)throws Exception;
    public ResultMessage updateOpenRequest(RequestOpen requestOpen);

    public RequestModify getModifyRequest(int id);
    public RequestModify loadModifyRequest(int id);
    public List<RequestModify> getModifyRequestByRestrictEqual(String column, Object value);
    public List<RequestModify> getModifyRequestByRestrictEqual(Map<String,Object> map);
    public int addModifyRequest(RequestModify requestModify)throws Exception;
    public ResultMessage updateModifyRequest(RequestModify requestModify);

}
