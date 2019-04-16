/*
Chase Mitchell
002274657
Mitch213@mail.chapman.edu
CPSC-408-01
Assignment3
*/

import java.sql.*;

public class DBConfigure
{
    public static getSQLConnection(String ip, String db, String user, string pass)
    {
        try
        {
            return DriverManager.getConnection(String.format("jdbc:mysql://%1$s:3306/%2$s?useSSL=false", ip, db),
                    user, pass);
        }
        catch (java.sql.SQLException e)
        {
            System.out.println(e);
        }
        return null;

    }



    
}