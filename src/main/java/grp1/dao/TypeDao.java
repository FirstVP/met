package grp1.dao;

import grp1.DataBaseConnectionTester;
import grp1.model.Type;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 22.05.2017.
 */
public class TypeDao {
    private Connection connection;

    public TypeDao() {
        DataBaseConnectionTester tester = new DataBaseConnectionTester();
        this.connection = tester.getConnection();
    }

    public List getAllTypes() throws SQLException {
        ArrayList<Type> list = new ArrayList<Type>();
        PreparedStatement ps= connection.prepareStatement(
                "select * from types");
        try
        {
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {
                Integer type_id = rs.getInt("type_id");
                String type_name= rs.getString("type_name");
                String type_image= rs.getString("type_image");
                list.add(new Type(type_id, type_name, type_image));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
            if (ps != null)
                ps.close();
            if (connection != null)
                connection.close();

        }
        return list;
    }

    public Type getType(Integer id)
    {
        return null;
    }

    public Type getTypeById(Integer id) throws SQLException {
        Type type = null;
        PreparedStatement ps= connection.prepareStatement(
                "select * from types where type_id=?");
        try
        {
            ps.setInt( 1, id );
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {
                Integer type_id = rs.getInt("type_id");
                String type_name= rs.getString("type_name");
                String type_image= rs.getString("type_image");
                type = new Type(type_id, type_name, type_image);

            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
            if (ps != null)
                ps.close();
            if (connection != null)
                connection.close();

        }
        return type;
    }
}
