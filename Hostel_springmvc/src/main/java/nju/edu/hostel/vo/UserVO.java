package nju.edu.hostel.vo;

import nju.edu.hostel.model.User;

/**
 * Created by disinuo on 17/3/4.
 */
public class UserVO {
    private int id;
    private String userName;
    private String password;
    private String type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public UserVO() {
    }
}
