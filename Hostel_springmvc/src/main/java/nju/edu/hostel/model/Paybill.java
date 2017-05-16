package nju.edu.hostel.model;

import nju.edu.hostel.util.DateHandler;
import nju.edu.hostel.util.NumberFormatter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by disinuo on 17/3/3.
 */
@Entity
@Table(name = "paybill", schema = "hostel", catalog = "")
public class PayBill {
    private int id;
    private Vip vip;
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
    @JoinColumn(name = "livebillId", referencedColumnName = "id", nullable = false)
    public LiveBill getLiveBill() {
        return liveBill;
    }

    public void setLiveBill(LiveBill liveBill) {
        this.liveBill = liveBill;
        this.vip=liveBill.getVip();
        this.hostel=liveBill.getHostel();
    }

    @Transient
    public Hostel getHostel() {
        return hostel;
    }
    @Transient
    public String getUserRealName() {
        return liveBill.getUserRealName();
    }

    @Transient
    public String getIdCard() {
        return liveBill.getIdCard();
    }

    @Transient
    public Vip getVip() {
        return vip;
    }

    @Transient
    public String getCreateDateStr(){
        return DateHandler.longToStr(this.createDate);
    }

}
