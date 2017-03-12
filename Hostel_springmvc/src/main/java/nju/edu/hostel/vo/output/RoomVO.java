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
}
