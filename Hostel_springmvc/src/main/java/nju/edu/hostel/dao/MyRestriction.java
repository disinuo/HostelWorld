package nju.edu.hostel.dao;

/**
 * Created by disinuo on 17/5/28.
 */
public class MyRestriction {
    private StringBuilder content;

    public MyRestriction(){
        this.content=new StringBuilder();
    }
    public MyRestriction(String str){
        this.content=new StringBuilder(str);
    }
    public void add(String str){
        this.content.append(" AND "+str);
    }
    public String getContent(){
        return this.content.toString();
    }
}
