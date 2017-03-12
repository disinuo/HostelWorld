package nju.edu.hostel.vo.input;

import nju.edu.hostel.model.Hostel;
import nju.edu.hostel.model.Room;
import nju.edu.hostel.model.Vip;

import java.util.Date;

/**
 * Created by disinuo on 17/3/6.
 */
public class BookVO {
    private String liveInDate;
    private String liveOutDate;
    private int vipId;
    private int roomId;

    public String getLiveOutDate() {
        return liveOutDate;
    }

    public void setLiveOutDate(String liveOutDate) {
        this.liveOutDate = liveOutDate;
    }

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

    public BookVO(String liveInDate,String liveOutDate, int vipId, int roomId) {
        this.liveInDate = liveInDate;
        this.vipId = vipId;
        this.roomId = roomId;
        this.liveOutDate=liveOutDate;
    }
    public BookVO(){}
}
