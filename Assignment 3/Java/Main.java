/*
Chase Mitchell
002274657
Mitch213@mail.chapman.edu
CPSC-408-01
Assignment3
*/

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Main
{
    public static void main(String[] args) throws SQLException
    {
        // Set Connection
        Connection conn = DBConfigure.getMySQLConnection("35.247.56.84", "DBMain", "rene", "LuggageCombination");

        try
        {
            boolean dropDB = true;  // Control To Clear Tables Before Upload; True By Default
            String filePath = "FakerData.csv";  // File Path Can Be Modified Here If Necessary
            List<List<String>> data = CSVReader.readCSV(filePath);  // Reads CSV Data And Stores For Later use

            DBManager manager = new DBManager(conn, data, dropDB); // Initialize Variables
            manager.initializeDB(); // Creates Tables If Not Exists
            manager.populateDB();  // Inserts Data Into Tables

        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        finally
        {
            assert conn != null;
            conn.close();
        }

    }

}