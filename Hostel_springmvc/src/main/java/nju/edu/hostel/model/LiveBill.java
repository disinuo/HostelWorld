package nju.edu.hostel.model;

import nju.edu.hostel.util.DateHandler;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by disinuo on 17/3/3.
 */
@Entity
@Table(name = "livebill", schema = "hostel", catalog = "")
public class LiveBill {
    private int id;
    private boolean inHostel=true;//初始是true，代表还没离店。false代表已离店
    private boolean paid=false;//false表示未支付。
    private String userRealName;
    private String idCard;
    private long date;
    private long checkOutDate;
    private Hostel hostel;
    private Vip vip;
    private Room room;
    private BookBill bookBill;

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
    @Column(name = "inhostel", nullable = false)
    public boolean getInHostel() {
        return inHostel;
    }
    public void setInHostel(boolean inHostel) {
        this.inHostel=inHostel;
    }

    @Basic
    @Column(name = "paid", nullable = false)
    public boolean isPaid() {
        return paid;
    }
    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    @Basic
    @Column(name = "userRealName", nullable = false)
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
    @Column(name = "date", nullable = false)
    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    @Basic
    @Column(name = "checkOutDate", nullable = true)
    public long getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(long checkOutDate) {
        this.checkOutDate = checkOutDate;
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
        this.hostel=room.getHostel();
    }
    @OneToOne
    @JoinColumn(name = "bookbillId", referencedColumnName = "id", nullable = true)

    public BookBill getBookBill() {
        return bookBill;
    }

    public void setBookBill(BookBill bookBill) {
        this.bookBill = bookBill;
    }

    @Transient
    public String getDateStr(){
        return DateHandler.longToStr(this.date);
    }
    @Transient
    public String getCheckOutDateStr(){
        return DateHandler.longToStr(this.checkOutDate);
    }
    @Transient
    public Hostel getHostel() {
        return hostel;
    }

}
