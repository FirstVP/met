package grp1;

import java.util.Map;

import grp1.dao.LoginDao;
import grp1.model.User;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

public class Login implements SessionAware{
    private String username,userpass;
    private User user;

    public SessionMap<String, Object> getSessionMap() {
        return sessionMap;
    }

    private SessionMap<String,Object> sessionMap;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpass() {
        return userpass;
    }

    public void setUserpass(String userpass) {
        this.userpass = userpass;
    }

    public String execute(){
        user = LoginDao.validate(username, userpass);
        if ((user != null)){
            if (sessionMap != null)
            {
                sessionMap.put("user_name", user.getName());
                sessionMap.put("role_id", user.getRole());
            }
            return "success";
        }
        else{
            return "error";
        }
    }

    @Override
    public void setSession(Map<String, Object> map) {
        sessionMap=(SessionMap)map;
    }

    public String logout(){
        if(sessionMap!=null){
            sessionMap.invalidate();
        }
        return "success";
    }

}