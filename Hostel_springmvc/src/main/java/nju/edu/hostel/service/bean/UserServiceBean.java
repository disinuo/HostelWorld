package nju.edu.hostel.service.bean;

import nju.edu.hostel.service.UserService;
import nju.edu.hostel.util.ResultMessage;
import nju.edu.hostel.util.UserType;
import nju.edu.hostel.model.*;

import java.util.List;

/**
 * Created by disinuo on 17/3/3.
 */
public class UserServiceBean implements UserService {
    @Override
    public ResultMessage add(String userName, String password) {
        return null;
    }

    @Override
    public ResultMessage delete(int userId) {
        return null;
    }

    @Override
    public ResultMessage update(User user) {
        return null;
    }

    @Override
    public User getById(int userId) {
        return null;
    }

    @Override
    public List<User> getByType(UserType type) {
        return null;
    }

    @Override
    public UserType login(String userName, String password) {
        return null;
    }
}
