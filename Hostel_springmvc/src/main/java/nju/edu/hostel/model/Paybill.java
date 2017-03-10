package nju.edu.hostel.model;

import nju.edu.hostel.util.DateHandler;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by disinuo on 17/3/3.
 */
@Entity
@Table(name = "paybill", schema = "hostel", catalog = "")
public class PayBill {
    private int id;
    private boolean counted=false;
    private String userRealName;
    private String idCard;
    private double money;
    private long createDate;
    private Hostel hostel;
    private Vip vip;
    private Room room;

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
    @Column(name = "counted", nullable = false)
    public boolean getCounted() {
        return counted;
    }

    public void setCounted(boolean counted) {
        this.counted = counted;
    }

    @Basic
    @Column(name = "userRealName", nullable = false, length = 255)
    public String getUserRealName() {
        return userRealName;
    }

    public void setUserRealName(String userRealName) {
        this.userRealName = userRealName;
    }

    @Basic
    @Column(name = "idCard", nullable = false, length = 255)
    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    @Basic
    @Column(name = "money", nullable = false, precision = 0)
    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    @Basic
    @Column(name = "createDate", nullable = false)
    public long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(long createDate) {
        this.createDate = createDate;
    }


    @ManyToOne
    @JoinColumn(name = "hostelId", referencedColumnName = "id", nullable = false)
    public Hostel getHostel() {
        return hostel;
    }

    public void setHostel(Hostel hostel) {
        this.hostel = hostel;
    }

    @ManyToOne
    @JoinColumn(name = "vipId", referencedColumnName = "id")
    public Vip getVip() {
        return vip;
    }

    public void setVip(Vip vip) {
        this.vip = vip;
    }

    @ManyToOne
    @JoinColumn(name = "roomId", referencedColumnName = "id", nullable = false)
    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
    @Transient
    public String getCreateDateStr(){
        return DateHandler.longToStr(this.createDate);
    }
}
