/*
Chase Mitchell
002274657
Mitch213@mail.chapman.edu
CPSC-408-01
Assignment3
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;


public class CSVReader
{
    public static List<List<String>> readCSV(String filename) throws FileNotFoundException
    {
        Scanner s = new Scanner(new File(filename));
        s.useDelimiter(","); // Parse via ,

        List<List<String>> columns = new ArrayList<List<String>>();
        System.out.println("File Preload Start");
        while(s.hasNext())
        {
            String t = s.nextLine();
            List<String> row = Arrays.asList(t.split(","));
            //System.out.println(row);
            columns.add(row);
        }
        s.close();

        System.out.println("File Preload Completed");
        return columns;

    }


}