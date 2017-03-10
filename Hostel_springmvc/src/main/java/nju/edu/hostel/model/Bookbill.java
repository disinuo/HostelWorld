package nju.edu.hostel.model;

import nju.edu.hostel.util.DateHandler;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by disinuo on 17/3/3.
 */
@Entity
@Table(name = "bookbill", schema = "hostel", catalog = "")
public class BookBill {
    private int id;
    private long liveInDate;
    private long createDate;
    private Hostel hostel;
    private Vip vip;
    private Room room;
    private boolean valid=true;//如果类型为true，valid为false 就代表该预订已被取消


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
    @Column(name = "valid", nullable = false)
    public boolean isValid() {
        return valid;
    }
    public void setValid(boolean valid) {
        this.valid = valid;
    }


    @Basic
    @Column(name = "liveInDate", nullable = false)
    public long getLiveInDate() {
        return liveInDate;
    }

    public void setLiveInDate(long liveInDate) {
        this.liveInDate = liveInDate;
    }

    @Basic
    @Column(name = "createDate", nullable = true)
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
    @Transient
    public String getLiveInDateStr(){
        return DateHandler.longToStr(this.liveInDate);
    }
    @Transient
    public int getVipId(){return this.vip.getId();}
}
