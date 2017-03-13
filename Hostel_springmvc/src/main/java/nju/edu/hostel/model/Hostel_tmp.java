package nju.edu.hostel.model;

/**
 * Created by disinuo on 17/3/13.
 */

import nju.edu.hostel.util.NumberFormatter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name = "hostel_tmp", schema = "hostel", catalog = "")
public class Hostel_tmp {
    private int id;
    private String img;
    private String phone="66668888";
    private String address;
    private String name;

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
    @Column(name = "img", nullable = true, length = 255)
    public String getImg() {
        return img;
    }
    public void setImg(String img) {
        this.img = img;
    }

    @Basic
    @Column(name = "phone", nullable = false, length = 255)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "address", nullable = false, length = 255)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
