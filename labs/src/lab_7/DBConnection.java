package lab_7;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnection
{
    private Connection connection;
    public Connection getConnection() { return connection; }
    public DBConnection() { }

    public void init()
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bd7", "root", "qwe123");
        }
        catch (ClassNotFoundException | SQLException e) { e.printStackTrace(); }
    }

    public void close(ResultSet rs)
    {
        if (rs != null)
        {
            try
            {
                rs.close();
            }
            catch (SQLException throwables) { throwables.printStackTrace(); }
        }
    }

    public void close(java.sql.Statement stmt)
    {
        if (stmt != null)
        {
            try
            {
                stmt.close();
            }
            catch (SQLException throwables) { throwables.printStackTrace(); }
        }
    }

    public void destroy()
    {
        if (connection != null)
        {
            try
            {
                connection.close();
            }
            catch (SQLException throwables) { throwables.printStackTrace(); }
        }
    }
}
