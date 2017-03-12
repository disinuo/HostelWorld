package nju.edu.hostel.vo.output;

import nju.edu.hostel.model.Hostel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by disinuo on 17/3/12.
 */
public class HostelVO {
    private int id;
    private boolean permitted;
    private String img;
    private String phone;
    private String address;
    private String name;
    private double moneyUncounted;

    public HostelVO(Hostel hostelEntity){
        this.id=hostelEntity.getId();
        this.permitted=hostelEntity.getPermitted();
        this.img=hostelEntity.getImg();
        this.phone=hostelEntity.getPhone();
        this.address=hostelEntity.getAddress();
        this.name=hostelEntity.getName();
        this.moneyUncounted=hostelEntity.getMoneyUncounted();
    }
    public static List<HostelVO> entityToVO(List<Hostel> hostels){
        List<HostelVO> res=new ArrayList<HostelVO>();
        for(Hostel hostel:hostels){
            res.add(new HostelVO((hostel)));
        }
        return res;
    }

    public int getId() {
        return id;
    }

    public boolean isPermitted() {
        return permitted;
    }

    public String getImg() {
        return img;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public double getMoneyUncounted() {
        return moneyUncounted;
    }
}
