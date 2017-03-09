package nju.edu.hostel.dao.Impl;

import nju.edu.hostel.dao.BaseDao;
import nju.edu.hostel.dao.RoomDao;
import nju.edu.hostel.model.Room;
import nju.edu.hostel.util.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by disinuo on 17/3/3.
 */
@Repository
public class RoomDaoImpl implements RoomDao {
    @Autowired
    BaseDao baseDao;

    @Override
    public Room get(int id) {
        return baseDao.getEntity(Room.class,id);
    }

    @Override
    public Room load(int id) {
        return baseDao.loadProxy(Room.class,id);
    }

    @Override
    public List<Room> getByRestrictEqual(String column, Object value) {

        return baseDao.getByRestrictEqual(Room.class,column,value);
    }

    @Override
    public List<Room> getByRestrictEqual(Map<String, Object> map) {
        return baseDao.getByRestrictEqual(Room.class,map);

    }

    @Override
    public int add(Room room) throws Exception {
        return baseDao.save(room);
    }

    @Override
    public ResultMessage update(Room room) {
        return baseDao.update(room);
    }
}
