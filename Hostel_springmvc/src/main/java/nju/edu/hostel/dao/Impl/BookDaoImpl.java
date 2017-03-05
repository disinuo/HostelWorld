package nju.edu.hostel.dao.Impl;

import nju.edu.hostel.dao.BaseDao;
import nju.edu.hostel.dao.BookDao;
import nju.edu.hostel.model.BookBill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by disinuo on 17/3/3.
 */
@Repository
public class BookDaoImpl implements BookDao{
    @Autowired
    BaseDao baseDao;
    @Override
    public List<BookBill> getBookBillsByVipId(int vipId) {
        String[] key={"vipId"};
        Object[] value={vipId};
        return baseDao.findByColunms(BookBill.class,key,value);
    }
}
