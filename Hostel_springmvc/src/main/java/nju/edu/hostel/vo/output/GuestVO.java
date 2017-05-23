package nju.edu.hostel.vo.output;

import nju.edu.hostel.model.LiveDetail;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by disinuo on 17/5/23.
 */
public class GuestVO {
    private int id;
    private String userRealName;
    private String idCard;
    private int vipId=-1;


    public GuestVO(LiveDetail detailEntity) {
        this.id = detailEntity.getId();
        this.userRealName=detailEntity.getUserRealName();
        this.idCard=detailEntity.getIdCard();
        this.vipId=(detailEntity.getVip()!=null)?(detailEntity.getVip().getId()):-1;


    }
    public static List<GuestVO> entityToVO(List<LiveDetail> details){
        List<GuestVO> ans=new ArrayList<GuestVO>();
        for(LiveDetail detail:details){
            ans.add(new GuestVO(detail));
        }
        return ans;
    }

    public int getId() {
        return id;
    }

    public String getUserRealName() {
        return userRealName;
    }

    public String getIdCard() {
        return idCard;
    }

    public int getVipId() {
        return vipId;
    }


}
