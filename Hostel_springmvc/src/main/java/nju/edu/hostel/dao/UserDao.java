package nju.edu.hostel.dao;

import nju.edu.hostel.model.User;
import nju.edu.hostel.util.ResultMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by disinuo on 17/3/3.
 */
public interface UserDao{
    public User get(int id);
    public User load(int id);
    public List<User> getByRestrictEqual(String column, Object value);
    public List<User> getByRestrictEqual(Map<String,Object> map);

    public int add(User user)throws Exception;
    public ResultMessage update(User user);


}
