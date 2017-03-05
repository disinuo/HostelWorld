package nju.edu.hostel.test;

import nju.edu.hostel.model.Hostel;
import nju.edu.hostel.model.Room;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by disinuo on 17/3/5.
 */
public class StubData {
    public static List<Room> getRooms(){
        List<Room> rooms=new ArrayList<>();
        for(int i=0;i<10;i++){
            Room room=new Room();
            room.setName("房间"+i);
            room.setId(i+100);
            room.setImg("../jpg");
            room.setPrice(100+i*5);
            room.setValid(true);
            room.setHostel(getHostels().get(i));
            rooms.add(room);
        }
        return rooms;
    }
    public static List<Hostel> getHostels(){
        List<Hostel> hostels=new ArrayList<>();
        for(int i=0;i<10;i++){
            Hostel hostel=new Hostel();
            hostel.setId(i);
            hostel.setAddress("啦啦啦街道"+i+"号");
            hostel.setName("客栈"+i);
            hostel.setPermitted(true);
            hostel.setPhone("00000"+i);
            hostels.add(hostel);
        }
        return hostels;
    }
}
