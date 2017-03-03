package nju.edu.hostel.model;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by disinuo on 17/3/3.
 */
@Entity
@Table(name = "vip", schema = "hostel", catalog = "")
public class Vip {
    private int id;
    private String realName;
    private String idCard;
    private String avatar;
    private double moneyLeft;
    private double moneyPaid;
    private int level;
    private double score;
    private String state;
    private int time;
    private Collection<Bookbill> bookbills;
    private Collection<Livebill> liveBillsById;
    private Collection<Paybill> paybills;

    @Id
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
        return moneyLeft;
    }

    public void setMoneyLeft(double moneyLeft) {
        this.moneyLeft = moneyLeft;
    }

    @Basic
    @Column(name = "moneyPaid", nullable = false, precision = 0)
    public double getMoneyPaid() {
        return moneyPaid;
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
        return score;
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
    @Column(name = "time", nullable = false)
    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vip vipEntity = (Vip) o;

        if (id != vipEntity.id) return false;
        if (Double.compare(vipEntity.moneyLeft, moneyLeft) != 0) return false;
        if (Double.compare(vipEntity.moneyPaid, moneyPaid) != 0) return false;
        if (level != vipEntity.level) return false;
        if (Double.compare(vipEntity.score, score) != 0) return false;
        if (time != vipEntity.time) return false;
        if (realName != null ? !realName.equals(vipEntity.realName) : vipEntity.realName != null) return false;
        if (idCard != null ? !idCard.equals(vipEntity.idCard) : vipEntity.idCard != null) return false;
        if (avatar != null ? !avatar.equals(vipEntity.avatar) : vipEntity.avatar != null) return false;
        if (state != null ? !state.equals(vipEntity.state) : vipEntity.state != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (realName != null ? realName.hashCode() : 0);
        result = 31 * result + (idCard != null ? idCard.hashCode() : 0);
        result = 31 * result + (avatar != null ? avatar.hashCode() : 0);
        temp = Double.doubleToLongBits(moneyLeft);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(moneyPaid);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + level;
        temp = Double.doubleToLongBits(score);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + time;
        return result;
    }

    @OneToMany(mappedBy = "vip")
    public Collection<Bookbill> getBookbills() {
        return bookbills;
    }

    public void setBookbills(Collection<Bookbill> bookbills) {
        this.bookbills = bookbills;
    }

    @OneToMany(mappedBy = "vip")
    public Collection<Livebill> getLiveBillsById() {
        return liveBillsById;
    }

    public void setLiveBillsById(Collection<Livebill> liveBillsById) {
        this.liveBillsById = liveBillsById;
    }

    @OneToMany(mappedBy = "vip")
    public Collection<Paybill> getPaybills() {
        return paybills;
    }

    public void setPaybills(Collection<Paybill> paybills) {
        this.paybills = paybills;
    }
}
