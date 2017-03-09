package nju.edu.hostel.util;

/**
 * Created by disinuo on 17/3/4.
 */
public enum VIPState {
    /**
     * 未激活
     */
    UNACTIVATED("未激活"),
    /**
     * 正常
     */
    NORMAL("正常"),
    /**
     * 已暂停
     */
    PAUSED("已暂停"),
    /**
     * 已停止。不可恢复
     */
    STOP("已停卡");

    public static VIPState strToVipState(String str){
        for(VIPState st:VIPState.values()){
            if(st.toString().toLowerCase().equals(str.toLowerCase())){
                return st;
            }
        }
        return NORMAL;
    }
    public String toChineseString() {
        return this.name;
    }
    private VIPState(String str){this.name=str;}
    private String name;
}
