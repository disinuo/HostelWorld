package nju.edu.hostel.respository;

import nju.edu.hostel.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by disinuo on 17/3/3.
 */
@Repository
public interface RoomRespository extends JpaRepository<Room, Integer> {
}