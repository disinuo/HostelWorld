package nju.edu.hostel.vo.input;

/**
 * Created by disinuo on 17/3/11.
 */
public class LiveInVO {
    private String userRealName;
    private String idCard;
    private int vipId=0;

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


    public LiveInVO(){}
}
