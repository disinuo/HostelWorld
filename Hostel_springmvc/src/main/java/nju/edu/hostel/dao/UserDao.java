package nju.edu.hostel.dao;

import nju.edu.hostel.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by disinuo on 17/3/3.
 */
public interface UserDao{
    public boolean ifExist(int id);
    public User getById(int id);
    public List<User> findByColunms(String[] columns, Object[] values);
}
