package grp1.dao;

import grp1.DataBaseConnectionTester;
import grp1.model.City;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by admin on 22.05.2017.
 */
public class CityDao {
    private Connection connection;

    public CityDao() {
        DataBaseConnectionTester tester = new DataBaseConnectionTester();
        this.connection = tester.getConnection();
    }

    public List getAllCities() throws SQLException {
        ArrayList<City> list = new ArrayList<City>();
        PreparedStatement ps= connection.prepareStatement(
                "select * from cities");
        try
        {
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {
                Integer city_id = rs.getInt("city_id");
                Integer city_code = rs.getInt("city_code");
                String city_name= rs.getString("city_name");
                Integer city_rise= rs.getInt("city_rise");
                Integer city_square= rs.getInt("city_square");
                Integer city_population= rs.getInt("city_population");
                list.add(new City(city_id, city_code, city_name, city_rise, city_square, city_population));
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

    public City getCity(Integer id)
    {
        return null;
    }

    public int update(City city) throws SQLException {
        int result = 0;
        PreparedStatement ps= connection.prepareStatement(
                "update cities set city_code=?, city_name=?, city_rise=?, city_square=?, city_population=? where city_id=?");
        try
        {
            ps.setInt( 1, city.getCode() );
            ps.setString( 2, city.getName() );
            ps.setInt( 3, city.getRise() );
            ps.setInt( 4, city.getSquare() );
            ps.setInt( 5, city.getPopulation() );
            ps.setInt( 6, city.getCityId() );
            result = ps.executeUpdate();
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
        return result;
    }

    public int insert(City city) throws SQLException {
        int result = 0;
        PreparedStatement ps= connection.prepareStatement(
                "insert into cities (city_code, city_name, city_rise, city_square, city_population) values (?,?,?,?,?)");
        try
        {
            ps.setInt( 1, city.getCode() );
            ps.setString( 2, city.getName() );
            ps.setInt( 3, city.getRise() );
            ps.setInt( 4, city.getSquare() );
            ps.setInt( 5, city.getPopulation() );
            result = ps.executeUpdate();
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
        return result;
    }

    public int delete(Integer id) throws SQLException {
        int result = 0;
        PreparedStatement ps = connection.prepareStatement(
                "delete from cities where city_id=?");
        try
        {
            ps.setInt( 1, id );
            result = ps.executeUpdate();
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
        return result;
    }

    public City getCityById(Integer id) throws SQLException {
        City city = null;
        PreparedStatement ps= connection.prepareStatement(
                "select * from cities where city_id=?");
        try
        {
            ps.setInt( 1, id );
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {
                Integer city_id = rs.getInt("city_id");
                Integer city_code = rs.getInt("city_code");
                String city_name= rs.getString("city_name");
                Integer city_rise= rs.getInt("city_rise");
                Integer city_square= rs.getInt("city_square");
                Integer city_population= rs.getInt("city_population");
                city = new City(city_id, city_code, city_name, city_rise, city_square, city_population);

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
        return city;
    }
}
