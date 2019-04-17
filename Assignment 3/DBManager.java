/*
Chase Mitchell
002274657
Mitch213@mail.chapman.edu
CPSC-408-01
Assignment3
*/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class DBManager
{
    private Connection conn;
    private List<List<String>> data;

    public DBManger(Connection c, List<List<String>> d)
    {
        this.conn = c;
        this.data = d;
    }

    public void initializeDatabase() throws SQLException
    {
        System.out.println("Test");

    }

    public void populateDatabase() throws SQLException
    {
        System.out.println("Test2");

    }

    private void clearDatabase() throws SQLException
    {

    }

    public void printDatabase() throws SQLException
    {
        System.out.println("Test3");

    }

}