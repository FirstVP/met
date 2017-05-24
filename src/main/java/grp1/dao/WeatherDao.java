package grp1.dao;

import grp1.DataBaseConnectionTester;
import grp1.model.Weather;

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
public class WeatherDao {
    private Connection connection;

    public WeatherDao() {
        DataBaseConnectionTester tester = new DataBaseConnectionTester();
        this.connection = tester.getConnection();
    }

    public List getAllWeathers() throws SQLException {
        ArrayList<Weather> list = new ArrayList<Weather>();
        PreparedStatement ps= connection.prepareStatement(
                "select * from weathers order by weather_date");
        try
        {
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {
                Integer weather_id = rs.getInt("weather_id");
                Integer type_id = rs.getInt("type_id");
                Integer city_id= rs.getInt("city_id");
                Integer temp= rs.getInt("temp");
                Integer wind= rs.getInt("wind");
                Integer pressure= rs.getInt("pressure");
                Date date= rs.getDate("weather_date");
                list.add(new Weather(weather_id, type_id, city_id, temp, wind, pressure, date));
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

    public Weather getWeather(Integer id)
    {
        return null;
    }

    public int update(Weather weather) throws SQLException {
        int result = 0;
        PreparedStatement ps= connection.prepareStatement(
                "update weathers set type_id=?, city_id=?, temp=?, wind=?, pressure=?, weather_date=? where weather_id=?");
        try
        {
            ps.setInt( 1, weather.getTypeId() );
            ps.setInt( 2, weather.getCityId() );
            ps.setInt( 3, weather.getTemp() );
            ps.setInt( 4, weather.getWind() );
            ps.setInt( 5, weather.getPressure() );
            ps.setDate(6, new java.sql.Date(weather.getDate().getTime()));
            ps.setInt( 7, weather.getWeatherId() );
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

    public int insert(Weather weather) throws SQLException {
        int result = 0;
        PreparedStatement ps= connection.prepareStatement(
                "insert into weathers (type_id, city_id, temp, wind, pressure, weather_date) values (?,?,?,?,?,?)");
        try
        {
            ps.setInt( 1, weather.getTypeId() );
            ps.setInt( 2, weather.getCityId() );
            ps.setInt( 3, weather.getTemp() );
            ps.setInt( 4, weather.getWind() );
            ps.setInt( 5, weather.getPressure() );
            ps.setDate(6, new java.sql.Date(weather.getDate().getTime()));
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
                "delete from weathers where weather_id=?");
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

    public Weather getWeatherById(Integer id) throws SQLException {
        Weather weather = null;
        PreparedStatement ps= connection.prepareStatement(
                "select * from weathers where weather_id=?");
        try
        {
            ps.setInt( 1, id );
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {
                Integer weather_id = rs.getInt("weather_id");
                Integer type_id = rs.getInt("type_id");
                Integer city_id= rs.getInt("city_id");
                Integer temp= rs.getInt("temp");
                Integer wind= rs.getInt("wind");
                Integer pressure= rs.getInt("pressure");
                Date date= rs.getDate("weather_date");
                weather = new Weather(weather_id, type_id, city_id, temp, wind, pressure, date);

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
        return weather;
    }
}
