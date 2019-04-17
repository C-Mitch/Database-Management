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
        s.useDelimiter(",");

        List<List<String>> columns = new ArrayList<List<String>>();

        while(s.hasNext())
        {
            String t = s.nextLine();
            List<String> row = Arrays.asList(t.split(","));

            columns.add(row);
        }
        s.close();

        return columns;

    }


}