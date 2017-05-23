package nju.edu.hostel.model;

import nju.edu.hostel.util.DateHandler;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

/**
 * Created by disinuo on 17/3/3.
 */
@Entity
@Table(name = "livebill", schema = "hostel", catalog = "")
public class LiveBill {
    private int id;
    private boolean inHostel=true;//初始是true，代表还没离店。false代表已离店
    private boolean paid=false;//false表示未支付。

    private long date;
    private long checkOutDate;

    private BookBill bookBill;
    private int numOfPeople;
    private Hostel hostel;
    private List<LiveDetail> liveDetails;


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
    @Basic
    @Column(name = "numOfPeople", nullable = false)
    public int getNumOfPeople() {
        return numOfPeople;
    }

    public void setNumOfPeople(int numOfPeople) {
        this.numOfPeople = numOfPeople;
    }

    @OneToOne
    @JoinColumn(name = "bookbillId", referencedColumnName = "id", nullable = true)

    public BookBill getBookBill() {
        return bookBill;
    }

    public void setBookBill(BookBill bookBill) {
        this.bookBill = bookBill;
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
    public String getDateStr(){
        return DateHandler.longToStr(this.date);
    }
    @Transient
    public String getCheckOutDateStr(){
        return DateHandler.longToStr(this.checkOutDate);
    }

    @OneToMany(mappedBy = "liveBill")
    public List<LiveDetail> getLiveDetails() {
        return liveDetails;
    }

    public void setLiveDetails(List<LiveDetail> liveDetails) {
        this.liveDetails = liveDetails;
    }
}
