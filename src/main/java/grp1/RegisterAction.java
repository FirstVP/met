package grp1;

import com.opensymphony.xwork2.ActionSupport;
import grp1.dao.RegisterDao;
import org.apache.struts2.interceptor.validation.SkipValidation;

/**
 * Created by admin on 09.05.2017.
 */
public class RegisterAction extends ActionSupport {
    public static Integer errorCount = 0;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String name, password, email;


    public String execute(){
        int result= RegisterDao.save(this);
        if(result > 0){
            return "success";
        }
        return "error";
    }

    private boolean isEmptyString ( String value )
    {
        return value == null || "".equals ( value.trim () );
    }

    public void validate ()
    {
        errorCount = 0;
        if ( isEmptyString ( name ))
        {
            addFieldError ( "name", "Name is empty" );
            errorCount++;
        }

        if ( isEmptyString ( password ))
        {
            addFieldError ( "password", "Password is empty" );
            errorCount++;
        }

        if ( isEmptyString ( email ))
        {
            addFieldError ( "email", "Email is empty" );
            errorCount++;
        }

        }


    public String input ()
    {

        return INPUT;
    }
}
