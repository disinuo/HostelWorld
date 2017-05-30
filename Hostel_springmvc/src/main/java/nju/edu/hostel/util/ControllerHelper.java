package nju.edu.hostel.util;

import nju.edu.hostel.vo.output.OnLineUserVO;

import javax.servlet.http.HttpSession;

/**
 * Created by disinuo on 17/5/30.
 */
public class ControllerHelper {
    public static int getUserIdFromSession(HttpSession session){
        OnLineUserVO user=(OnLineUserVO)session.getAttribute("user");
        return user.getId();
    }
}
