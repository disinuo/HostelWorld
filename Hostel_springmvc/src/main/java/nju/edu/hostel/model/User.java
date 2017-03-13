package nju.edu.hostel.model;


import nju.edu.hostel.util.NumberFormatter;

import javax.persistence.*;

/**
 * Created by disinuo on 17/3/3.
 */
@Entity
@Table(name = "user", schema = "hostel", catalog = "")
public class User {
    private int id;
    private String userName;
    private String password="root";
    private String type="vip";
    private String bankId;//AutoSet = 111111111111 + id
    private String bankPassword="bankroot";
    private double bankMoney=3000;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        String bankId="111111111111"+id;
        setBankId(bankId);
    }

    @Basic
    @Column(name = "userName", nullable = false, length = 255)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 255)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "type", nullable = false, length = 255)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "bankId", nullable = false, length = 255)
    public String getBankId() {
        return bankId;
    }
    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    @Basic
    @Column(name = "bankPassword", nullable = false, length = 255)
    public String getBankPassword() {
        return bankPassword;
    }

    public void setBankPassword(String bankPassword) {
        this.bankPassword = bankPassword;
    }

    @Basic
    @Column(name = "bankMoney", nullable = false, length = 255)
    public double getBankMoney() {
        return  NumberFormatter.saveOneDecimal(bankMoney);
    }

    public void setBankMoney(double bankMoney) {
        this.bankMoney = bankMoney;
    }
}
