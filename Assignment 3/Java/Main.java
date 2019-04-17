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
        //Connection conn = DBConfigure.getMySQLConnection("35.247.56.84", "DBMain", "test", "test");
        Connection conn = DBConfigure.getMySQLConnection("35.247.56.84", "DBMain", "rene", "LuggageCombination");

        try
        {
            String filePath = "FakerData.csv";
            List<List<String>> data = CSVReader.readCSV(filePath);

            DBManager manager = new DBManager(conn, data);
            manager.initializeDB();
            manager.populateDB();

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