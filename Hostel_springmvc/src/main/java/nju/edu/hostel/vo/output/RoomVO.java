package nju.edu.hostel.vo.output;

import nju.edu.hostel.model.Room;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by disinuo on 17/3/12.
 */

public class RoomVO {
    private int id;
    private double price;
    private String img;
    private int state;
    private String name;

    private int hostelId;
    private String hostelPhone;
    private String hostelName;
    private String hostelAddress;

    //the max num of people the room can hold
    private int capacity;

    //此类型房间总数
    private int totalNum;
    //此类型房间空闲数
    private int vacantNum;
    private int bookedNum=0;

    //生效起始时间
    private String startDate;
    //生效结束时间
    private String endDate;

    private String descrip;


    public RoomVO(Room roomEntity){
        this.id=roomEntity.getId();
        this.price=roomEntity.getPrice();
        this.img=roomEntity.getImg();
        this.state=roomEntity.getState();
        this.name=roomEntity.getName();
        this.hostelId=roomEntity.getHostel().getId();
        this.hostelPhone=roomEntity.getHostel().getPhone();
        this.hostelName=roomEntity.getHostel().getName();
        this.hostelAddress=
            roomEntity.getHostel().getProvince()+" - "+
            roomEntity.getHostel().getCity()+" - "+
            roomEntity.getHostel().getAddress();
        this.capacity=roomEntity.getCapacity();
        this.totalNum=roomEntity.getTotalNum();
        this.vacantNum=roomEntity.getVacantNum();
        this.startDate=roomEntity.getStartDateStr();
        this.endDate=roomEntity.getEndDateStr();
        this.descrip=roomEntity.getDescrip();
        this.bookedNum=roomEntity.getBookedNum();
    }

    public static List<RoomVO> entityToVO(List<Room> rooms){
        List<RoomVO> res=new ArrayList<RoomVO>();
        for(Room room:rooms){
            res.add(new RoomVO(room));
        }
        return res;
    }

    public int getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public String getImg() {
        return img;
    }

    public int getState() {
        return state;
    }

    public String getName() {
        return name;
    }

    public int getHostelId() {
        return hostelId;
    }

    public String getHostelPhone() {
        return hostelPhone;
    }

    public String getHostelName() {
        return hostelName;
    }

    public String getHostelAddress() {
        return hostelAddress;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public int getBookedNum() {
        return bookedNum;
    }

    public int getVacantNum() {
        return vacantNum;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getDescrip() {
        return descrip;
    }
}
