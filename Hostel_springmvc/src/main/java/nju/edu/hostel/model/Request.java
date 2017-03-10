package nju.edu.hostel.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by disinuo on 17/3/11.
 */
@Entity
@Table(name = "request", schema = "hostel", catalog = "")

public class Request {
    private int id;
    private Hostel hostel;
    private String state;

    @Id
    @GenericGenerator(name="dsn" , strategy="increment")
    @GeneratedValue(generator="dsn")
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    @ManyToOne
    @JoinColumn(name = "hostelId", referencedColumnName = "id", nullable = false)
    public Hostel getHostel() {
        return hostel;
    }
    public void setHostel(Hostel hostel) {
        this.hostel = hostel;
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
