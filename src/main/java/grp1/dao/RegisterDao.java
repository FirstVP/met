package grp1.dao;

import grp1.DataBaseConnectionTester;
import grp1.RegisterAction;

import java.sql.*;

/**
 * Created by admin on 09.05.2017.
 */
public class RegisterDao {

    public static int save(RegisterAction r){
        int status=0;
        try{
            DataBaseConnectionTester tester = new DataBaseConnectionTester();
            Connection connection = tester.getConnection();

            PreparedStatement ps=connection.prepareStatement("insert into users values(NULL,?,?,?,1)");
            ps.setString(1,r.getName());
            ps.setString(2,r.getPassword());
            ps.setString(3,r.getEmail());

            status=ps.executeUpdate();

        }catch(Exception e){e.printStackTrace();}
        return status;
    }
}