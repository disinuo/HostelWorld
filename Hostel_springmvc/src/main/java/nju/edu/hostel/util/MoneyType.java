package nju.edu.hostel.util;

/**
 * Created by disinuo on 17/5/24.
 */
public enum  MoneyType {

    MONEY_TYPE_PAY(0,"vip消费"),//vip消费
    MONEY_TYPE_BOOK(1,"预订"),//vip预订
    MONEY_TYPE_UNBOOK(2,"取消预订"),//vip取消预订
    MONEY_TYPE_BOOK_LIVED(3,"预订入住退款"),
    MONEY_TYPE_TOPUP(4,"充值"),//从银行卡充值
    MONEY_TYPE_SCORE_TO_MONEY(5,"积分换钱"),//积分换钱
    MONEY_TYPE_COUNT(6,"结算"),//总经理结算
    MONEY_TYPE_ROOM_COST(7,"房间成本"),//客栈新增房间时会扣除的
    MONEY_TYPE_ERROR(404,"未知类型"),//未知类型
    MONEY_TYPE_IN(666,"收入"),//未知的收入
    MONEY_TYPE_OUT(777,"支出"),//未知的支出





    ;

    public static MoneyType codeToType(int code){
        for(MoneyType type:MoneyType.values()){
            if(type.code==code)return type;
        }
        return MONEY_TYPE_ERROR;
    }

    public String toChineseStr(){return this.name;}
    private MoneyType(int code,String name){
        this.code=code;
        this.name=name;
    }
    public int getCode(){return code;}
    private int code;
    private String name;
}
