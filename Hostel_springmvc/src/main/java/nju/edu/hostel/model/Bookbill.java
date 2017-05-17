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
    private long liveOutDate;
    private long createDate;
    private Vip vip;
    private Room room;
    /*
     * [默认]0：预订未入住
     * -1：已取消
     * 1： 已入住
     */
    private int state=0;

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
    @Column(name = "state", nullable = false)
    public int getState() {
        return state;
    }
    public void setState(int state) {
        this.state = state;
    }
    @Basic
    @Column(name = "liveOutDate", nullable = false)
    public long getLiveOutDate() {
        return liveOutDate;
    }
    public void setLiveOutDate(long liveOutDate) {
        this.liveOutDate = liveOutDate;
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


    @Transient
    public Hostel getHostel() {
        return room.getHostel();
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
    public String getLiveOutDateStr(){
        return DateHandler.longToStr(this.liveOutDate);
    }
    @Transient
    public int getVipId(){return this.vip.getId();}
}
