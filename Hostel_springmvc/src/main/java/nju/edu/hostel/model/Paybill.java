package nju.edu.hostel.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

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
    private Date createDate;
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
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PayBill that = (PayBill) o;

        if (id != that.id) return false;
        if (counted != that.counted) return false;
        if (Double.compare(that.money, money) != 0) return false;
        if (userRealName != null ? !userRealName.equals(that.userRealName) : that.userRealName != null) return false;
        if (idCard != null ? !idCard.equals(that.idCard) : that.idCard != null) return false;
        if (createDate != null ? !createDate.equals(that.createDate) : that.createDate != null) return false;

        return true;
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
}
