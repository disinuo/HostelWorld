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
    String baseHql="SELECT room FROM Room as room WHERE ";

    @Override
    public Room get(int id) {
        return baseDao.getEntity(Room.class,id);
    }

    @Override
    public Room load(int id) {
        return baseDao.loadProxy(Room.class,id);
    }

//
//    @Override
//    public List<Room> getNotPassed(int hostelId){
//        String hql=baseHql+ "room.hostel.id = "+hostelId+
//                " AND room.state > -1 "+" ORDER BY room.id DESC";
//        return baseDao.getByHql(Room.class,hql);
//    }

    @Override
    public List<Room> getByHostel(int hostelId, String base) {
        String hql=baseHql+"room.hostel.id="+hostelId+" ORDER BY "+
                "room."+base+" DESC";
        return baseDao.getByHql(Room.class,hql);
    }

    @Override
    public int add(Room room) throws Exception {
        return baseDao.save(room);
    }
    @Override
    public ResultMessage add(List<Room> rooms){
        for(Room room:rooms){
            try {
                baseDao.save(room);
            } catch (Exception e) {
                e.printStackTrace();
                return ResultMessage.FAILURE;
            }
        }
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage update(Room room) {
        return baseDao.update(room);
    }
}
