package nju.edu.hostel.vo.input;

/**
 * Created by disinuo on 17/3/11.
 */
public class LiveInVO {
    private String userRealName;
    private String idCard;
    private int vipId=0;
    private int roomId;
    private int bookBillId;

    public String getUserRealName() {
        return userRealName;
    }

    public void setUserRealName(String userRealName) {
        this.userRealName = userRealName;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
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

    public int getBookBillId() {
        return bookBillId;
    }

    public void setBookBillId(int bookBillId) {
        this.bookBillId = bookBillId;
    }

    public LiveInVO(String userRealName, String idCard, int vipId, int roomId) {
        this.userRealName = userRealName;
        this.idCard = idCard;
        this.vipId = vipId;
        this.roomId = roomId;
    }
    public LiveInVO(){}
}
