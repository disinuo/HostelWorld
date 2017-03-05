package nju.edu.hostel.dao;

import nju.edu.hostel.model.Hostel;

import java.util.List;

/**
 * Created by disinuo on 17/3/3.
 */
public interface HostelDao {
    public List<Hostel> get();
    public Hostel getById(int hostelId);
}
