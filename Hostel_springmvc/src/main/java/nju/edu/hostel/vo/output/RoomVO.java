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
    private boolean valid;
    private String name;

    private int hostelId;
    private String hostelPhone;
    private String hostelName;
    private String hostelAddress;

    //the max num of people the room can hold
    private int capacity;

    //此类型房间总数
    private int totalNum;
    //此类型房间已入住数
    private int occupiedNum=0;
    //生效起始时间
    private long startDate;
    //生效结束时间
    private long endDate;

    private String descrip;


    public RoomVO(Room roomEntity){
        this.id=roomEntity.getId();
        this.price=roomEntity.getPrice();
        this.img=roomEntity.getImg();
        this.valid=roomEntity.getValid();
        this.name=roomEntity.getName();
        this.hostelId=roomEntity.getHostel().getId();
        this.hostelPhone=roomEntity.getHostel().getPhone();
        this.hostelName=roomEntity.getHostel().getName();
        this.hostelAddress=roomEntity.getHostel().getAddress();
        this.capacity=roomEntity.getCapacity();
        this.totalNum=roomEntity.getTotalNum();
        this.occupiedNum=roomEntity.getOccupiedNum();
        this.startDate=roomEntity.getStartDate();
        this.endDate=roomEntity.getEndDate();
        this.descrip=roomEntity.getDescrip();

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

    public boolean isValid() {
        return valid;
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

    public int getOccupiedNum() {
        return occupiedNum;
    }

    public long getStartDate() {
        return startDate;
    }

    public long getEndDate() {
        return endDate;
    }

    public String getDescrip() {
        return descrip;
    }
}
