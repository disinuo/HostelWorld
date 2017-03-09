package nju.edu.hostel.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by disinuo on 17/3/7.
 */
@Entity
@Table(name = "for_test", schema = "hostel", catalog = "")
public class Test_Table{
    private int id;
    private String name;
     private double money;

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
    @Column(name = "name", nullable = false, length = 255)
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "money", nullable = false, length = 255)
    public double getBankMoney() {
        return money;
    }
    public void setBankMoney(double money) {
        this.money = money;
    }
}
