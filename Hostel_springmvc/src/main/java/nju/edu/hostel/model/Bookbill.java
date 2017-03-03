package nju.edu.hostel.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by disinuo on 17/3/3.
 */
@Entity
@Table(name = "bookbill", schema = "hostel", catalog = "")
public class Bookbill {
    private int id;
    private Date liveInDate;
    private Date createDate;
    private Hostel hostel;
    private Vip vip;
    private Room room;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "liveInDate", nullable = false)
    public Date getLiveInDate() {
        return liveInDate;
    }

    public void setLiveInDate(Date liveInDate) {
        this.liveInDate = liveInDate;
    }

    @Basic
    @Column(name = "createDate", nullable = true)
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bookbill that = (Bookbill) o;

        if (id != that.id) return false;
        if (liveInDate != null ? !liveInDate.equals(that.liveInDate) : that.liveInDate != null) return false;
        if (createDate != null ? !createDate.equals(that.createDate) : that.createDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (liveInDate != null ? liveInDate.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "hostelId", referencedColumnName = "id", nullable = false)
    public Hostel getHostel() {
        return hostel;
    }

    public void setHostel(Hostel hostel) {
        this.hostel = hostel;
    }

    @ManyToOne
    @JoinColumn(name = "vipId", referencedColumnName = "id")
    public Vip getVip() {
        return vip;
    }

    public void setVip(Vip vip) {
        this.vip = vip;
    }

    @ManyToOne
    @JoinColumn(name = "roomId", referencedColumnName = "id", nullable = false)
    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
