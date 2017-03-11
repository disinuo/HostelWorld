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
    private Hostel hostelNew;
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
    @ManyToOne
    @JoinColumn(name = "hostelOriginal", referencedColumnName = "id", nullable = false)
    public Hostel getHostelOriginal() {
        return hostelOriginal;
    }
    public void setHostelOriginal(Hostel hostelOriginal) {
        this.hostelOriginal = hostelOriginal;
    }

    @ManyToOne
    @JoinColumn(name = "hostelNew", referencedColumnName = "id", nullable = false)
    public Hostel getHostelNew() {
        return hostelNew;
    }
    public void setHostelNew(Hostel hostelNew) {
        this.hostelNew = hostelNew;
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
