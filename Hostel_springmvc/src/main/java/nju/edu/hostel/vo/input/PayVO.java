package nju.edu.hostel.vo.input;

/**
 * Created by disinuo on 17/3/11.
 */
public class PayVO {
    private double money;
    private int liveBillId;

    public PayVO(int liveBillId,double money) {
        this.money = money;
        this.liveBillId=liveBillId;
    }

    public int getLiveBillId() {
        return liveBillId;
    }

    public void setLiveBillId(int liveBillId) {
        this.liveBillId = liveBillId;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }


    public PayVO(){}
}
