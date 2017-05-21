package grp1.dao;

import grp1.DataBaseConnectionTester;
import grp1.model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginDao {

    public static User validate(String username,String userpass){
        boolean status=false;
        User result = null;
        try{
            DataBaseConnectionTester tester = new DataBaseConnectionTester();
            Connection connection = tester.getConnection();

            PreparedStatement ps=connection.prepareStatement(
                    "select * from users where user_name=? and user_password=?");
            ps.setString(1,username);
            ps.setString(2,userpass);
            ResultSet rs=ps.executeQuery();
            int count = 0;
            while (rs.next()) {
                count++;

                String user_name = rs.getString("user_name");
                String user_password = rs.getString("user_password");
                String user_email= rs.getString("user_email");
                int role_id = rs.getInt("role_id");
                result = new User(user_name, user_password, user_email, role_id);
                System.out.println("user_name : " + user_name);
                System.out.println("user_password : " + user_password);

            }
            if (count > 0)
                status = true;
        }catch(Exception e){e.printStackTrace();}
        return result;
    }
}