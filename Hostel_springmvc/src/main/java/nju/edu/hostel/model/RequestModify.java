package nju.edu.hostel.model;

import nju.edu.hostel.util.RequestState;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by disinuo on 17/3/11.
 */
@Entity
@Table(name = "request_modify", schema = "hostel", catalog = "")

public class RequestModify {
    private int id;
    private Hostel hostelOriginal;
    private String newPhone;
    private String newName;
    private String newAddress;
    private String newImg;
    private String state= RequestState.UNCHECKED.toString();

    @Id
    @GenericGenerator(name="dsn" , strategy="increment")
    @GeneratedValue(generator="dsn")
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "newPhone", nullable = false)
    public String getNewPhone() {
        return newPhone;
    }
    public void setNewPhone(String newPhone) {
        this.newPhone = newPhone;
    }

    @Basic
    @Column(name = "newName", nullable = false)
    public String getNewName() {
        return newName;
    }
    public void setNewName(String newName) {
        this.newName = newName;
    }

    @Basic
    @Column(name = "newAddress", nullable = false)
    public String getNewAddress() {
        return newAddress;
    }
    public void setNewAddress(String newAddress) {
        this.newAddress = newAddress;
    }

    @Basic
    @Column(name = "newImg", nullable = true)
    public String getNewImg() {
        return newImg;
    }

    public void setNewImg(String newImg) {
        this.newImg = newImg;
    }

    @ManyToOne
    @JoinColumn(name = "hostelOriginal", referencedColumnName = "id", nullable = false)
    public Hostel getHostelOriginal() {
        return hostelOriginal;
    }
    public void setHostelOriginal(Hostel hostelOriginal) {
        this.hostelOriginal = hostelOriginal;
    }

    @Basic
    @Column(name = "state", nullable = false)
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
}
