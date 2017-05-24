package nju.edu.hostel.model;

import javax.persistence.*;

/**
 * Created by disinuo on 17/5/25.
 */
@Entity
@Table(name = "boss_money_record", schema = "hostel", catalog = "")
public class BossMoneyRecord {
    private int id;
    private int bossId;
    private double money;
    private long date;
    private int type;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "bossId", nullable = false)
    public int getBossId() {
        return bossId;
    }

    public void setBossId(int bossId) {
        this.bossId = bossId;
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
    @Column(name = "date", nullable = false)
    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    @Basic
    @Column(name = "type", nullable = false)
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

}
