package nju.edu.hostel.vo.output;

import nju.edu.hostel.model.RequestOpen;
import nju.edu.hostel.util.RequestState;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by disinuo on 17/3/12.
 */

public class RequestOpenVO {
    private int id;
    private int hostel_id;
    private String hostel_img;
    private String hostel_phone;
    private String hostel_address;
    private String hostel_name;
    private RequestState state;

    public RequestOpenVO(RequestOpen request){
        this.id=request.getId();
        this.hostel_id=request.getHostel().getId();
        this.hostel_img=request.getHostel().getImg();
        this.hostel_phone=request.getHostel().getPhone();
        this.hostel_address=request.getHostel().getAddress();
        this.hostel_name=request.getHostel().getName();
        this.state=RequestState.strToRequestState(request.getState());
    }

    public static List<RequestOpenVO> entityToVO(List<RequestOpen> requests){
        List<RequestOpenVO> res=new ArrayList<RequestOpenVO>();
        for(RequestOpen request:requests){
            res.add(new RequestOpenVO(request));
        }
        return res;
    }

    public int getId() {
        return id;
    }

    public String getHostel_img() {
        return hostel_img;
    }

    public String getHostel_phone() {
        return hostel_phone;
    }

    public String getHostel_address() {
        return hostel_address;
    }

    public String getHostel_name() {
        return hostel_name;
    }

    public RequestState getState() {
        return state;
    }
}