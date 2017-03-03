package nju.edu.hostel.model;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by disinuo on 17/3/3.
 */
@Entity
@Table(name = "hostel", schema = "hostel", catalog = "")
public class Hostel {
    private int id;
    private byte permitted;
    private String phone;
    private String address;
    private String name;
    private Collection<Bookbill> bookbills;
    private Collection<Livebill> livebills;
    private Collection<Paybill> paybills;
    private Collection<Room> rooms;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "permitted", nullable = false)
    public byte getPermitted() {
        return permitted;
    }

    public void setPermitted(byte permitted) {
        this.permitted = permitted;
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
    @Column(name = "name", nullable = true, length = 255)
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

        Hostel that = (Hostel) o;

        if (id != that.id) return false;
        if (permitted != that.permitted) return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (int) permitted;
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "hostel")
    public Collection<Bookbill> getBookbills() {
        return bookbills;
    }

    public void setBookbills(Collection<Bookbill> bookbills) {
        this.bookbills = bookbills;
    }

    @OneToMany(mappedBy = "hostel")
    public Collection<Livebill> getLivebills() {
        return livebills;
    }

    public void setLivebills(Collection<Livebill> livebills) {
        this.livebills = livebills;
    }

    @OneToMany(mappedBy = "hostel")
    public Collection<Paybill> getPaybills() {
        return paybills;
    }

    public void setPaybills(Collection<Paybill> paybills) {
        this.paybills = paybills;
    }

    @OneToMany(mappedBy = "hostel")
    public Collection<Room> getRooms() {
        return rooms;
    }

    public void setRooms(Collection<Room> rooms) {
        this.rooms = rooms;
    }
}
