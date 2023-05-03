package term.project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {
    public static List<List<String>> readCSVFile(String csvFilePath){
        List<List<String>> data = new ArrayList<>();
        String line;
        String cvsSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {

            while ((line = br.readLine()) != null) {

                String[] values = line.split(cvsSplitBy);

                List<String> row = new ArrayList<>();
                for (String value : values) {
                    row.add(value.trim());
                }

                data.add(row);
            }

        } catch (IOException e) {
             System.out.println("Error reading CSV file: File could not be found. Check URL.");
             System.exit(0);
        }

        return data;
    }
}
