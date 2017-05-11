package nju.edu.hostel.model;

import nju.edu.hostel.util.NumberFormatter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Created by disinuo on 17/3/3.
 */
@Entity
@Table(name = "hostel", schema = "hostel", catalog = "")
public class Hostel {
    private int id;
    private boolean permitted=false;
    private String img;
    private String phone="66668888";
    private String address="默认地址";
    private String name;
    private double moneyUncounted;
    private List<BookBill> bookBills;
    private List<LiveBill> liveBills;
    private List<PayBill> payBills;
    private List<Room> rooms;

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
    @Column(name = "permitted", nullable = false)
    public boolean getPermitted() {
        return permitted;
    }
    public void setPermitted(boolean permitted) {
        this.permitted = permitted;
    }

    @Basic
    @Column(name = "moneyUncounted", nullable = false)
    public double getMoneyUncounted() {
        return NumberFormatter.saveOneDecimal(moneyUncounted);
    }
    public void setMoneyUncounted(double moneyUncounted) {
        this.moneyUncounted = moneyUncounted;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Hostel hostel = (Hostel) o;

        if (id != hostel.id) return false;
        if (permitted != hostel.permitted) return false;
        if (Double.compare(hostel.moneyUncounted, moneyUncounted) != 0) return false;
        if (img != null ? !img.equals(hostel.img) : hostel.img != null) return false;
        if (!phone.equals(hostel.phone)) return false;
        if (!address.equals(hostel.address)) return false;
        return name.equals(hostel.name);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (permitted ? 1 : 0);
        result = 31 * result + (img != null ? img.hashCode() : 0);
        result = 31 * result + phone.hashCode();
        result = 31 * result + address.hashCode();
        result = 31 * result + name.hashCode();
        temp = Double.doubleToLongBits(moneyUncounted);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @OneToMany(mappedBy = "hostel")
    public List<BookBill> getBookBills() {
        return bookBills;
    }

    public void setBookBills(List<BookBill> bookBills) {
        this.bookBills = bookBills;
    }

    @OneToMany(mappedBy = "hostel")
    public List<LiveBill> getLiveBills() {
        return liveBills;
    }

    public void setLiveBills(List<LiveBill> liveBills) {
        this.liveBills = liveBills;
    }

    @OneToMany(mappedBy = "hostel")
    public List<PayBill> getPayBills() {
        return payBills;
    }

    public void setPayBills(List<PayBill> payBills) {
        this.payBills = payBills;
    }

    @OneToMany(mappedBy = "hostel")
    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

}
