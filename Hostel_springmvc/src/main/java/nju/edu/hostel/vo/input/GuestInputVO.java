package nju.edu.hostel.vo.input;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by disinuo on 17/3/11.
 */
public class GuestInputVO {
    private String userRealName;
    private String idCard;
    private int vipId=-1;

    public String getUserRealName() {
        return userRealName;
    }

    public void setUserRealName(String userRealName) {
        this.userRealName = userRealName;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public int getVipId() {
        return vipId;
    }

    public void setVipId(int vipId) {
        this.vipId = vipId;
    }


    public GuestInputVO(){}
    public GuestInputVO(Map<String,Object> map){
        this.userRealName=(String)map.get("userRealName");
        this.idCard=(String)map.get("idCard");
        try{
            this.vipId=Integer.parseInt((String)map.get("vipId"));
        }catch (Exception e){
            this.vipId=-1;
        }
    }
    public static List<GuestInputVO> mapToVO(List<Map<String,Object>> maps){
        List<GuestInputVO> vos=new ArrayList<GuestInputVO>();
        for(Map<String,Object> m:maps){
            vos.add(new GuestInputVO(m));
        }
        return vos;
    }
}
