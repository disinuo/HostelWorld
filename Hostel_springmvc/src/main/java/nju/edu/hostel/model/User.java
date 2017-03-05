package nju.edu.hostel.model;


import javax.persistence.*;

/**
 * Created by disinuo on 17/3/3.
 */
@Entity
@Table(name = "user", schema = "hostel", catalog = "")
public class User {
    private int id;
    private String userName;
    private String password;
    private String type;
    private String bankId;
    private String bankPassword;
    private String bankMoney;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    public String getBankMoney() {
        return bankMoney;
    }

    public void setBankMoney(String bankMoney) {
        this.bankMoney = bankMoney;
    }
}
