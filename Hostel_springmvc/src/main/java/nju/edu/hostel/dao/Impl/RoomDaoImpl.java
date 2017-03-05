package nju.edu.hostel.dao.Impl;

import nju.edu.hostel.dao.BaseDao;
import nju.edu.hostel.dao.RoomDao;
import nju.edu.hostel.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by disinuo on 17/3/3.
 */
@Repository
public class RoomDaoImpl implements RoomDao {
    @Autowired
    BaseDao baseDao;
    @Override
    public Room getById(int id) {
        return baseDao.getById(Room.class,id);
    }
}
