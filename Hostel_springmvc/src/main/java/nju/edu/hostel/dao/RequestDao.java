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
    public List<RequestOpen> getOpenRequestByHostel(int hostelId);
    public List<RequestOpen> getUncheckedOpenRequests();

    public RequestOpen loadOpenRequest(int id);
    public int addOpenRequest(RequestOpen requestOpen)throws Exception;
    public ResultMessage updateOpenRequest(RequestOpen requestOpen);



    public RequestModify getModifyRequest(int id);
    public List<RequestModify> getUncheckedModifyRequests();
    public RequestModify loadModifyRequest(int id);
    public int addModifyRequest(RequestModify requestModify)throws Exception;
    public ResultMessage updateModifyRequest(RequestModify requestModify);

}
