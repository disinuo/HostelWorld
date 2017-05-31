package nju.edu.hostel.util;

/**
 * Created by disinuo on 17/5/31.
 */
public enum RoomPriceRange {
    LT_100(100,"<100"),
    IN_100_200(200,"100~200"),
    IN_200_300(300,"200~300"),
    IN_300_500(500,"300~500"),
    IN_500_700(700,"500~700"),
    IN_700_1000(1000,"700~1000"),
    GE_1000(10000,">=1000");


    private RoomPriceRange(int index,String value){

        this.index=index;
        this.value=value;
    }
    public static RoomPriceRange priceToRange(double price){
        RoomPriceRange[] array=RoomPriceRange.values();
        for(RoomPriceRange r:array){
            if(price<r.getIndex()) return r;
        }
        return GE_1000;
    }
    public int getIndex(){return index;}
    public String getValue(){return value;}
    @Override
    public String toString(){return this.value;}
    private int index;
    private String value;
}
