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
    public List<Room> getByRestrictEqual(String column, Object value);
    public List<Room> getByRestrictEqual(Map<String,Object> map);

    /**
     * @param base：排序的基准
     * @return
     */
    public List<Room> getByRestrictEqual(String column, Object value,String base);
        /**
         * return rooms whose state= 0 or 1
         */
    public List<Room> getNotPassed(int hostelId);
    public int add(Room room)throws Exception;
    public ResultMessage add(List<Room> rooms);
    public ResultMessage update(Room room);


}
