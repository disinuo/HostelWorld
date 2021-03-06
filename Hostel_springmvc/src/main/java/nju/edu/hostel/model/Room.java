package nju.edu.hostel.model;

import nju.edu.hostel.util.DateHandler;
import nju.edu.hostel.util.NumberFormatter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by disinuo on 17/3/3.
 */
@Entity
@Table(name = "room", schema = "hostel", catalog = "")
public class Room {
    private int id;
    private double price=299;
    private String img="/../img/hostel001.jpg";
    /*
    -1 已下市；
    0 有效
    1 未上市
     */
    private int state=0;
    private String name;
    private Hostel hostel;

    //the max num of people the room can hold
    private int capacity;

    //此类型房间总数
    private int totalNum;
    //此类型房间空闲数
    private int vacantNum=0;
    //此类型房间预订数
    private int bookedNum=0;
    //生效起始时间
    private long startDate;
    //生效结束时间
    private long endDate;

    private String descrip;


    @Id
    @GenericGenerator(name="dsn" , strategy="increment")
    @GeneratedValue(generator="dsn")
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "descrip", nullable = true, length = -1)
    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }


    @Basic
    @Column(name = "price", nullable = false, precision = 0)
    public double getPrice() {
        return  NumberFormatter.saveOneDecimal(price);
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Basic
    @Column(name = "img", nullable = true, length = 255)
    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Basic
    @Column(name = "state", nullable = true)
    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Basic
    @Column(name = "capacity", nullable = false)
    public int getCapacity() {
        return capacity;
    }
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Basic
    @Column(name = "totalNum", nullable = false)
    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    @Basic
    @Column(name = "vacantNum", nullable = false)
    public int getVacantNum() {
        return vacantNum;
    }

    public void setVacantNum(int vacantNum) {
        this.vacantNum = vacantNum;
    }

    @Basic
    @Column(name = "bookedNum", nullable = false)
    public int getBookedNum() {
        return bookedNum;
    }
    public void setBookedNum(int bookedNum) {
        this.bookedNum = bookedNum;
    }

    @Basic
    @Column(name = "startDate", nullable = false)
    public long getStartDate() {
        return startDate;
    }

    public void setStartDate(long startDate) {
        this.startDate = startDate;
    }

    @Basic
    @Column(name = "endDate", nullable = false)
    public long getEndDate() {
        return endDate;
    }

    public void setEndDate(long endDate) {
        this.endDate = endDate;
    }

    @ManyToOne
    @JoinColumn(name = "hostelId", referencedColumnName = "id", nullable = false)
    public Hostel getHostel() {
        return hostel;
    }

    public void setHostel(Hostel hostel) {
        this.hostel = hostel;
    }


    @Transient
    public String getStartDateStr(){
        return DateHandler.longToStr_noTime(this.startDate);
    }
    @Transient
    public String getEndDateStr(){
        return DateHandler.longToStr_noTime(this.endDate);
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Room that = (Room) o;

        if (id != that.id) return false;
        if (Double.compare(that.price, price) != 0) return false;
        if (img != null ? !img.equals(that.img) : that.img != null) return false;
        if (state != that.state)  return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

}
