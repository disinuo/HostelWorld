package nju.edu.hostel.dao;

import nju.edu.hostel.model.Room;
import nju.edu.hostel.util.ResultMessage;

import java.util.List;
import java.util.Map;

/**
 * Created by disinuo on 17/3/3.
 */
public interface RoomDao {
    public Room get(int id);
    public Room load(int id);

    /**
     * return rooms whose state= 0 or 1
     */
//    public List<Room> getNotPassed(int hostelId);

    /**
     * 按room的base属性排序
     * @param hostelId
     * @return
     */
    public List<Room> getByHostel(int hostelId,String base);
    public int add(Room room)throws Exception;
    public ResultMessage add(List<Room> rooms);
    public ResultMessage update(Room room);


}
