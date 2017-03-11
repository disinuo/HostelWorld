package nju.edu.hostel.util;

/**
 * Created by disinuo on 17/3/11.
 */
public enum RequestState {
    UNCHECKED,APPROVED,DENIED;

    public static RequestState strToRequestState(String str){
        for(RequestState st:RequestState.values()){
            if(st.toString().toLowerCase().equals(str.toLowerCase())){
                return st;
            }
        }
        return UNCHECKED;
    }
}
