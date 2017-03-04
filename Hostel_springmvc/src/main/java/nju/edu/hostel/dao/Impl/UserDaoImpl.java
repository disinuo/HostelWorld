package nju.edu.hostel.dao.Impl;

import nju.edu.hostel.dao.BaseDao;
import nju.edu.hostel.dao.UserDao;
import nju.edu.hostel.util.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import nju.edu.hostel.model.*;

import java.util.List;

import static javafx.scene.input.KeyCode.T;

/**
 * Created by disinuo on 17/3/3.
 */
@Repository

public class UserDaoImpl  implements UserDao{
    @Autowired
    BaseDao baseDao;
    @Override
    public boolean ifExist(int id) {
//        System.out.println("UserDaoImpl  id= "+id);
//        String hql="FROM User user WHERE user.id=:id";
//        User user=(User) baseDao.findById(User.class, id);
//        if(user!=null) return true;
//        else return false;
        return true;
    }

    @Override
    public User getById(int id) {
        return null;
//        return (User)baseDao.findById(User.class,id);
    }

    @Override
    public List<User> findByColunms(String[] columns, Object[] values) {
        return baseDao.findByColunms(User.class,columns,values);
    }

//
//    @Override
//    public <S extends T> S save(S s) {
//        return null;
//    }
//
//    @Override
//    public User findOne(Integer integer) {
//        return null;
//    }
//
//    @Override
//    public boolean exists(Integer integer) {
//        return false;
//    }
//
//    @Override
//    public List<User> findAll() {
//        return null;
//    }
//
//    @Override
//    public List<User> findAll(Sort sort) {
//        return null;
//    }
//
//    @Override
//    public Page<User> findAll(Pageable pageable) {
//        return null;
//    }
//
//    @Override
//    public List<User> findAll(Iterable<Integer> iterable) {
//        return null;
//    }
//
//    @Override
//    public long count() {
//        return 0;
//    }
//
//    @Override
//    public void delete(Integer integer) {
//
//    }
//
//    @Override
//    public void delete(User user) {
//
//    }
//
//    @Override
//    public void delete(Iterable<? extends User> iterable) {
//
//    }
//
//    @Override
//    public void deleteAll() {
//
//    }
//
//    @Override
//    public void flush() {
//
//    }
//
//    @Override
//    public void deleteInBatch(Iterable<User> iterable) {
//
//    }
//
//    @Override
//    public void deleteAllInBatch() {
//
//    }
//
//    @Override
//    public User getOne(Integer integer) {
//        return null;
//    }
//
//    @Override
//    public <S extends T> List<S> findAll(Example<S> example, Sort sort) {
//        return null;
//    }
//
//    @Override
//    public <S extends T> List<S> findAll(Example<S> example) {
//        return null;
//    }
//
//    @Override
//    public <S extends T> S saveAndFlush(S s) {
//        return null;
//    }
//
//    @Override
//    public <S extends T> List<S> save(Iterable<S> iterable) {
//        return null;
//    }
//
//    @Override
//    public <S extends T> S findOne() {
//        return findOne();
//    }
//
//    @Override
//    public <S extends T> S findOne(Example<S> example) {
//        return null;
//    }
//
//    @Override
//    public <S extends T> Page<S> findAll(Example<S> example, Pageable pageable) {
//        return null;
//    }
//
//    @Override
//    public <S extends T> long count(Example<S> example) {
//        return 0;
//    }
//
//    @Override
//    public <S extends T> boolean exists(Example<S> example) {
//        return false;
//    }
}
