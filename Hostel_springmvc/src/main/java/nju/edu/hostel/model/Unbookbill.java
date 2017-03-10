package nju.edu.hostel.model;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by disinuo on 17/3/10.
 */
@Entity
@Table(name = "unbookbill", schema = "hostel", catalog = "")
public class Unbookbill {
    private int id;
    private BookBill bookBill;
    private Date createDate;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @OneToOne
    @JoinColumn(name = "bookId", referencedColumnName = "id",nullable = false)
    public BookBill getBookBill() {
        return bookBill;
    }

    public void setBookBill(BookBill bookBill) {
        this.bookBill = bookBill;
    }

    @Basic
    @Column(name = "createDate", nullable = true)
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

}
