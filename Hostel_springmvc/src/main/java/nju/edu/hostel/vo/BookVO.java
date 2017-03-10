package nju.edu.hostel.vo;

import nju.edu.hostel.model.Hostel;
import nju.edu.hostel.model.Room;
import nju.edu.hostel.model.Vip;

import java.util.Date;

/**
 * Created by disinuo on 17/3/6.
 */
public class BookVO {
    private String liveInDate;
    private int vipId;
    private int roomId;

    public String getLiveInDate() {
        return liveInDate;
    }

    public void setLiveInDate(String liveInDate) {
        this.liveInDate = liveInDate;
    }

    public int getVipId() {
        return vipId;
    }

    public void setVipId(int vipId) {
        this.vipId = vipId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }
}
