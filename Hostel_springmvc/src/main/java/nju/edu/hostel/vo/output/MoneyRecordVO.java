package nju.edu.hostel.vo.output;

import nju.edu.hostel.model.BossMoneyRecord;
import nju.edu.hostel.model.HostelMoneyRecord;
import nju.edu.hostel.model.VipMoneyRecord;
import nju.edu.hostel.util.DateHandler;
import nju.edu.hostel.util.MoneyType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by disinuo on 17/5/28.
 */
public class MoneyRecordVO {
    int userId;
    double money;
    String date;
    MoneyType type;

    public MoneyRecordVO(VipMoneyRecord record){
        this.userId=record.getVipId();
        this.money=record.getMoney();
        this.date= DateHandler.longToStr_noTime(record.getDate());
        this.type=MoneyType.codeToType(record.getType());
    }
    public MoneyRecordVO(HostelMoneyRecord record){
        this.userId=record.getHostelId();
        this.money=record.getMoney();
        this.date= DateHandler.longToStr_noTime(record.getDate());
        this.type=MoneyType.codeToType(record.getType());
    }
    public MoneyRecordVO(BossMoneyRecord record){
        this.userId=record.getBossId();
        this.money=record.getMoney();
        this.date= DateHandler.longToStr_noTime(record.getDate());
        this.type=MoneyType.codeToType(record.getType());
    }
    public static List<MoneyRecordVO> entityToVO_vip(List<VipMoneyRecord> records){
        List<MoneyRecordVO> res=new ArrayList<MoneyRecordVO>();
        for(VipMoneyRecord r:records){
            res.add(new MoneyRecordVO(r));
        }
        return res;
    }
    public static List<MoneyRecordVO> entityToVO_hostel(List<HostelMoneyRecord> records){
        List<MoneyRecordVO> res=new ArrayList<MoneyRecordVO>();
        for(HostelMoneyRecord r:records){
            res.add(new MoneyRecordVO(r));
        }
        return res;
    }
    public static List<MoneyRecordVO> entityToVO_boss(List<BossMoneyRecord> records){
        List<MoneyRecordVO> res=new ArrayList<MoneyRecordVO>();
        for(BossMoneyRecord r:records){
            res.add(new MoneyRecordVO(r));
        }
        return res;
    }



    public int getUserId() {
        return userId;
    }

    public double getMoney() {
        return money;
    }

    public String getDate() {
        return date;
    }

    public MoneyType getType() {
        return type;
    }
    public String getTypeStr() {
        return type.toChineseStr();
    }
}
