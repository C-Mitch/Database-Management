/*
Chase Mitchell
002274657
Mitch213@mail.chapman.edu
CPSC-408-01
Assignment3
*/

import java.sql.*;
import java.util.List;

public class DBManager
{
    private Connection conn;
    private List<List<String>> data;

    public DBManager(Connection c, List<List<String>> d)
    {
        this.conn = c;
        this.data = d;
    }

    public void initializeDB() throws SQLException
    {
        this.dropTables();
        System.out.println("In Initializer");
        List<String> headerNames = data.get(0);

        PreparedStatement s = conn.prepareStatement(
                "CREATE TABLE IF NOT EXISTS People(" +
                        "ID INTEGER PRIMARY KEY AUTO_INCREMENT," +            // ID
                        headerNames.get(0) + " VARCHAR(25)," +              // FirstName
                        headerNames.get(1) + " VARCHAR(25)," +              // LastName
                        headerNames.get(2) + " DATE," +                    // DOB
                        headerNames.get(3) + " VARCHAR(11)," +              // SSN
                        headerNames.get(7) + " VARCHAR(75))"                // Job

        );
        s.executeUpdate();


        System.out.println("Out Initializer");
        s.close();
    }

    public void populateDB() throws SQLException
    {
        System.out.println("In Populator");

        PreparedStatement s = null;
        for(int i = 1; i < data.size(); ++i)
        {
            List<String> row = data.get(i);
            String firstName = row.get(0);
            String lastName = row.get(1);
            Date dob = Date.valueOf(row.get(2));
            String ssn = row.get(3);
            String address = row.get(4);
            String email = row.get(5);
            String country = row.get(6);
            String job = row.get(7);
            String userName = row.get(8);
            String password = row.get(9);

            s = conn.prepareStatement("INSERT INTO People(FirstName, LastName, DateOfBirth, SSN, Job) VALUES(?,?,?,?,?)");
            s.setString(1, firstName);
            s.setString(2, lastName);
            s.setDate(3, dob);
            s.setString(4, ssn);
            s.setString(5, job);
            s.executeUpdate();
            s.clearParameters();




        }

        System.out.println("Out Populator");
    }

    private void dropTables() throws SQLException
    {
        System.out.println("In Dropper");

        PreparedStatement s = conn.prepareStatement("DROP TABLE IF EXISTS People");
        s.executeUpdate();

        s = conn.prepareStatement("DROP TABLE IF EXISTS Users");
        s.executeUpdate();

        s = conn.prepareStatement("DROP TABLE IF EXISTS Cars");
        s.executeUpdate();

        s = conn.prepareStatement("DROP TABLE IF EXISTS Jobs");
        s.executeUpdate();

        s = conn.prepareStatement("DROP TABLE IF EXISTS Addresses");
        s.executeUpdate();

        System.out.println("Out Dropper");
        s.close();
    }

    public void printDatabase() throws SQLException
    {
        System.out.println("Test3");

    }

}