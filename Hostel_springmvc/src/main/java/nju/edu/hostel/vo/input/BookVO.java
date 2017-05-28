package nju.edu.hostel.vo.input;

/**
 * Created by disinuo on 17/3/6.
 */
public class BookVO {
    private String liveInDate;
    private String checkOutDate;
    private int vipId;
    private int roomId;

    public String getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(String checkOutDate) {
        this.checkOutDate = checkOutDate;
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

    public BookVO(String liveInDate, String checkOutDate, int vipId, int roomId) {
        this.liveInDate = liveInDate;
        this.vipId = vipId;
        this.roomId = roomId;
        this.checkOutDate = checkOutDate;
    }
    public BookVO(){}
}
