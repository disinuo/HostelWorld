package nju.edu.hostel.vo.input;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by disinuo on 17/5/23.
 */
public class LiveInVO {
    private int bookBillId;
    private int roomId;
    private List<Map<String,Object>> guests;

    public int getBookBillId() {
        return bookBillId;
    }

    public void setBookBillId(int bookBillId) {
        this.bookBillId = bookBillId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public List<Map<String, Object>> getGuests() {
        return guests;
    }

    public void setGuests(List<Map<String, Object>> guests) {
        this.guests = guests;
    }
}
