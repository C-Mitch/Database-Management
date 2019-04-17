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
    // Global Variables
    private Connection conn;
    private List<List<String>> data;
    private Boolean drop;

    public DBManager(Connection c, List<List<String>> d, boolean t)
    {
        this.conn = c;
        this.data = d;
        this.drop = t;
    }

    public void initializeDB() throws SQLException // Creates tables if not exists, structured to be in 3NF
    {
        if(drop) this.dropTables();

        System.out.println("Start Table Initializer");
        List<String> headerNames = data.get(0);

        PreparedStatement s = conn.prepareStatement(
                "CREATE TABLE IF NOT EXISTS People(" +
                        "Person_ID INTEGER PRIMARY KEY NOT NULL ," +            // ID
                        headerNames.get(0) + " VARCHAR(25)," +                  // FirstName
                        headerNames.get(1) + " VARCHAR(25)," +                  // LastName
                        headerNames.get(2) + " DATE," +                         // DOB
                        headerNames.get(3) + " VARCHAR(11)," +                  // SSN
                        headerNames.get(7) + " VARCHAR(75)," +                  // Job
                        headerNames.get(9) + " VARCHAR(25))"                    // UserName

        );
        s.executeUpdate();

        s = conn.prepareStatement(
                "CREATE TABLE IF NOT EXISTS People_Locations(" +
                        headerNames.get(3) + " VARCHAR(11) PRIMARY KEY ," +     // SSN
                        headerNames.get(6) + " VARCHAR(75)," +                  // Country
                        headerNames.get(4) + " VARCHAR(75))"                    // Address

        );
        s.executeUpdate();

        s = conn.prepareStatement(
                "CREATE TABLE IF NOT EXISTS Accounts(" +
                        headerNames.get(9) + " VARCHAR(25) PRIMARY KEY ," +     // UserName
                        headerNames.get(10) + " VARCHAR(25)," +                 // Pass
                        headerNames.get(5) + " VARCHAR(75))"                    // Email

        );
        s.executeUpdate();

        s = conn.prepareStatement(
                "CREATE TABLE IF NOT EXISTS Jobs_Locations(" +
                        headerNames.get(8) + " VARCHAR(75) PRIMARY KEY ," +     // JobAddress
                        headerNames.get(7) + " VARCHAR(75))"                    // Job

        );
        s.executeUpdate();

        s = conn.prepareStatement(
                "CREATE TABLE IF NOT EXISTS ID_Address(" +
                        "Person_ID INTEGER NOT NULL ," +            // ID
                        headerNames.get(8) + " VARCHAR(75)," +      // JobAddress
                        "PRIMARY KEY(Person_ID, JobAddress))"

        );
        s.executeUpdate();


        System.out.println("Table Initializer Complete");
        s.close();
    }

    public void populateDB() throws SQLException  // Fill respective tables from passed data
    {
        System.out.println("Start Table Populator");

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
            String jobAddress = row.get(8);
            String userName = row.get(9);
            String password = row.get(10);
            int personID = Integer.valueOf(row.get(11));

            s = conn.prepareStatement("INSERT INTO People(Person_ID, FirstName, LastName, DateOfBirth, SSN, Job, UserName) VALUES(?,?,?,?,?,?,?)");
            s.setInt(1, personID);
            s.setString(2, firstName);
            s.setString(3, lastName);
            s.setDate(4, dob);
            s.setString(5, ssn);
            s.setString(6, job);
            s.setString(7, userName);
            s.executeUpdate();
            s.clearParameters();

            s = conn.prepareStatement("INSERT INTO People_Locations(SSN, Country, Address) VALUES(?,?,?)");
            s.setString(1, ssn);
            s.setString(2, country);
            s.setString(3, address);
            s.executeUpdate();
            s.clearParameters();

            s = conn.prepareStatement("INSERT INTO Accounts(UserName, Password, Email) VALUES(?,?,?) " +
                    "ON DUPLICATE KEY UPDATE UserName = values(UserName), Password = values(Password), Email = values(Email)");  // Update Info On Dupe Value
            s.setString(1, userName);
            s.setString(2, password);
            s.setString(3, email);
            s.executeUpdate();
            s.clearParameters();

            s = conn.prepareStatement("INSERT INTO Jobs_Locations(JobAddress, Job) VALUES(?,?)");
            s.setString(1, jobAddress);
            s.setString(2, job);
            s.executeUpdate();
            s.clearParameters();

            s = conn.prepareStatement("INSERT INTO ID_Address(Person_ID, JobAddress) VALUES(?,?)");
            s.setInt(1, personID);
            s.setString(2, jobAddress);
            s.executeUpdate();
            s.clearParameters();
        }
        assert s != null;
        s.close();

        System.out.println("Table Populator Complete");
    }

    private void dropTables() throws SQLException // Drops tables to clear old data
    {
        System.out.println("Starting Table Dropper");

        PreparedStatement s = conn.prepareStatement("DROP TABLE IF EXISTS People");
        s.executeUpdate();

        s = conn.prepareStatement("DROP TABLE IF EXISTS People_Locations");
        s.executeUpdate();

        s = conn.prepareStatement("DROP TABLE IF EXISTS Accounts");
        s.executeUpdate();

        s = conn.prepareStatement("DROP TABLE IF EXISTS Jobs_Locations");
        s.executeUpdate();

        s = conn.prepareStatement("DROP TABLE IF EXISTS ID_Address");
        s.executeUpdate();

        System.out.println("Table Dropper Completed");
        s.close();
    }

}