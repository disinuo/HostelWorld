package nju.edu.hostel.vo.output;

import nju.edu.hostel.model.User;

/**
 * Created by disinuo on 17/3/14.
 */
public class UserVO_output {
    private int id;
    private String userName;
    private String type;
    private String bankId;//AutoSet = 111111111111 + id
    private double bankMoney;

    public UserVO_output(User user){
        this.id=user.getId();
        this.userName=user.getUserName();
        this.type=user.getType();
        this.bankId=user.getBankId();
        this.bankMoney=user.getBankMoney();
    }
    public UserVO_output(){}
    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getType() {
        return type;
    }

    public String getBankId() {
        return bankId;
    }

    public double getBankMoney() {
        return bankMoney;
    }
}
