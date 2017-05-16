package nju.edu.hostel.model;

import nju.edu.hostel.util.NumberFormatter;
import nju.edu.hostel.util.VIPState;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

/**
 * Created by disinuo on 17/3/3.
 */
@Entity
@Table(name = "vip", schema = "hostel", catalog = "")
public class Vip {
    private int id;
    private String realName = "匿名";
    private String idCard = "111111197001011111";
    private String avatar;
    private double moneyLeft = 0;
    private double moneyPaid = 0;
    private int level = 0;
    private double score = 0;
    private String state = VIPState.UNACTIVATED.toString();
    private long activateDate;
    private long pauseDate;
    private String email;
    private String province = "北京";
    private String city = "北京";


    @Id
    @GenericGenerator(name = "dsn", strategy = "increment")
    @GeneratedValue(generator = "dsn")
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "realName", nullable = false, length = 255)
    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    @Basic
    @Column(name = "activateDate", nullable = false)
    public long getActivateDate() {
        return activateDate;
    }

    public void setActivateDate(long activateDate) {
        this.activateDate = activateDate;
    }

    @Basic
    @Column(name = "pauseDate", nullable = false)
    public long getPauseDate() {
        return pauseDate;
    }

    public void setPauseDate(long pauseDate) {
        this.pauseDate = pauseDate;
    }

    @Basic
    @Column(name = "idCard", nullable = true, length = 255)
    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    @Basic
    @Column(name = "avatar", nullable = true, length = 255)
    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Basic
    @Column(name = "moneyLeft", nullable = false, precision = 0)
    public double getMoneyLeft() {
        return NumberFormatter.saveOneDecimal(moneyLeft);
    }

    public void setMoneyLeft(double moneyLeft) {
        this.moneyLeft = moneyLeft;
    }

    @Basic
    @Column(name = "moneyPaid", nullable = false, precision = 0)
    public double getMoneyPaid() {
        return NumberFormatter.saveOneDecimal(moneyPaid);
    }

    public void setMoneyPaid(double moneyPaid) {
        this.moneyPaid = moneyPaid;
    }

    @Basic
    @Column(name = "level", nullable = false)
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Basic
    @Column(name = "score", nullable = false, precision = 0)
    public double getScore() {
        return NumberFormatter.saveOneDecimal(score);
    }

    public void setScore(double score) {
        this.score = score;
    }

    @Basic
    @Column(name = "state", nullable = false, length = 255)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Basic
    @Column(name = "email", nullable = true, length = 255)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "province", nullable = false, length = 255)
    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @Basic
    @Column(name = "city", nullable = false, length = 255)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

}
