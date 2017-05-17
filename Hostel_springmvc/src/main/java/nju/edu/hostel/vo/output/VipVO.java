package nju.edu.hostel.vo.output;

import nju.edu.hostel.model.Vip;
import nju.edu.hostel.util.VIPState;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by disinuo on 17/3/4.
 */
public class VipVO {
    private int id;
    private String realName;
    private String idCard;
    private String avatar;
    private double moneyLeft;
    private double moneyPaid;
    private int level;
    private double score;
    private VIPState state;

    private String email;
    private String province;
    private String city;

    public VipVO(Vip vipEntity){
        this.id = vipEntity.getId();
        this.realName = vipEntity.getRealName();
        this.idCard = vipEntity.getIdCard();
        this.avatar = vipEntity.getAvatar();
        this.moneyLeft = vipEntity.getMoneyLeft();
        this.moneyPaid = vipEntity.getMoneyPaid();
        this.level = vipEntity.getLevel();
        this.score = vipEntity.getScore();
        this.email=vipEntity.getEmail();
        this.state = VIPState.strToVipState(vipEntity.getState());
        this.province=vipEntity.getProvince();
        this.city=vipEntity.getCity();
    }

    public static List<VipVO> entityToVO(List<Vip> vips){
        List<VipVO> ans=new ArrayList<VipVO>();
        for (Vip vip:vips){
            ans.add(new VipVO(vip));
        }
        return ans;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public double getMoneyLeft() {
        return moneyLeft;
    }

    public void setMoneyLeft(double moneyLeft) {
        this.moneyLeft = moneyLeft;
    }

    public double getMoneyPaid() {
        return moneyPaid;
    }

    public void setMoneyPaid(double moneyPaid) {
        this.moneyPaid = moneyPaid;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public VIPState getState() {
        return state;
    }

    public void setState(VIPState state) {
        this.state = state;
    }
    public String getStateStr(){
        return state.toChineseString();
    }


    public String getEmail() {
        return email;
    }

    public String getProvince() {
        return province;
    }

    public String getCity() {
        return city;
    }
}
