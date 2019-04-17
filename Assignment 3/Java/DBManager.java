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
                        "Person_ID INTEGER PRIMARY KEY NOT NULL ," +            // ID
                        headerNames.get(0) + " VARCHAR(25)," +                  // FirstName
                        headerNames.get(1) + " VARCHAR(25)," +                  // LastName
                        headerNames.get(2) + " DATE," +                         // DOB
                        headerNames.get(3) + " VARCHAR(11)," +                  // SSN
                        headerNames.get(7) + " VARCHAR(50)," +                  // Job
                        headerNames.get(9) + " VARCHAR(25))"                    // UserName

        );
        s.executeUpdate();

        s = conn.prepareStatement(
                "CREATE TABLE IF NOT EXISTS People_Locations(" +
                        headerNames.get(3) + " VARCHAR(11) PRIMARY KEY ," +     // SSN
                        headerNames.get(6) + " VARCHAR(50)," +                  // Country
                        headerNames.get(4) + " VARCHAR(50))"                    // Address

        );
        s.executeUpdate();

        s = conn.prepareStatement(
                "CREATE TABLE IF NOT EXISTS Accounts(" +
                        headerNames.get(9) + " VARCHAR(25) PRIMARY KEY ," +     // UserName
                        headerNames.get(10) + " VARCHAR(25)," +                 // Pass
                        headerNames.get(5) + " VARCHAR(50))"                    // Email

        );
        s.executeUpdate();

        s = conn.prepareStatement(
                "CREATE TABLE IF NOT EXISTS Jobs_Locations(" +
                        headerNames.get(8) + " VARCHAR(50) PRIMARY KEY ," +     // JobAddress
                        headerNames.get(7) + " VARCHAR(50))"                    // Job

        );
        s.executeUpdate();

        s = conn.prepareStatement(
                "CREATE TABLE IF NOT EXISTS ID_Address(" +
                        "Person_ID INTEGER NOT NULL ," +            // ID
                        headerNames.get(8) + " VARCHAR(50)," +      // JobAddress
                        "PRIMARY KEY(Person_ID, JobAddress))"

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

            s = conn.prepareStatement("INSERT INTO Accounts(UserName, Password, Email) VALUES(?,?,?)");
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

        System.out.println("Out Populator");
    }

    private void dropTables() throws SQLException
    {
        System.out.println("In Dropper");

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

        System.out.println("Out Dropper");
        s.close();
    }

}