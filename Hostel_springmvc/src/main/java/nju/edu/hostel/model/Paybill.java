package nju.edu.hostel.model;

import nju.edu.hostel.util.DateHandler;
import nju.edu.hostel.util.NumberFormatter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

/**
 * Created by disinuo on 17/3/3.
 */
@Entity
@Table(name = "paybill", schema = "hostel", catalog = "")
public class PayBill {
    private int id;
    private boolean counted=false;//未被网站总经理结算
    private double money;
    private long createDate;
    private LiveBill liveBill;
    private Hostel hostel;


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
    @Column(name = "money", nullable = false, precision = 0)
    public double getMoney() {
        return  NumberFormatter.saveOneDecimal(money);
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
    @JoinColumn(name = "liveBillId", referencedColumnName = "id", nullable = false)
    public LiveBill getLiveBill() {
        return liveBill;
    }

    public void setLiveBill(LiveBill liveBill) {
        this.liveBill = liveBill;
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
    public Room getRoom(){
        return liveBill.getRoom();
    }

    @Transient
    public String getCreateDateStr(){
        return DateHandler.longToStr_noTime(this.createDate);
    }

    @Transient
    public int getNumOfPeople() {
        return liveBill.getNumOfPeople();
    }
    @Transient
    public List<LiveDetail> getLiveDetails() {
        return liveBill.getLiveDetails();
    }
}
