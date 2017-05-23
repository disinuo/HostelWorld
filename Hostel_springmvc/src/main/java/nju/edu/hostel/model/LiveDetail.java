package nju.edu.hostel.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by disinuo on 17/5/23.
 */
@Entity
@Table(name = "liveDetail", schema = "hostel", catalog = "")

public class LiveDetail {
    private int id;
    private String userRealName;
    private String idCard;
    private Vip vip;
    private LiveBill liveBill;
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

    @ManyToOne
    @JoinColumn(name = "vipId", referencedColumnName = "id")
    public Vip getVip() {
        return vip;
    }

    public void setVip(Vip vip) {
        this.vip = vip;
    }


    @ManyToOne
    @JoinColumn(name = "billId", referencedColumnName = "id", nullable = false)
    public LiveBill getLiveBill() {
        return liveBill;
    }

    public void setLiveBill(LiveBill liveBill) {
        this.liveBill = liveBill;
    }

}
