package nju.edu.hostel.dao.Impl;

import nju.edu.hostel.dao.BaseDao;
import nju.edu.hostel.model.Test_Table;
import nju.edu.hostel.util.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by disinuo on 17/3/7.
 */
@Repository
public class TestTableDaoImpl {
    @Autowired
    BaseDao baseDao;
    public int add(Test_Table table)throws Exception{
        return baseDao.save(table);
    }
}