package nju.edu.hostel.vo.input;

import nju.edu.hostel.model.User;

/**
 * Created by disinuo on 17/3/4.
 */
public class UserVO {
    private String userName;
    private String password;


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public UserVO() {
    }
}
