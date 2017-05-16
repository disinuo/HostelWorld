package nju.edu.hostel.vo.input;

/**
 * Created by disinuo on 17/3/11.
 */
public class RoomVO_input {
    private double price=299;
    private String img="/../img/hostel001.jpg";
    private String name;

    //the max num of people the room can hold
    private int capacity;

    //此类型房间总数
    private int totalNum;
    //生效起始时间
    private String startDate;
    //生效结束时间
    private String endDate;

    private String descrip;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public RoomVO_input(){}
}
