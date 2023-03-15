import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class CityParser {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(new File("cities.txt"));
            PrintWriter writer = new PrintWriter(new File("output.txt"));
            while (scanner.hasNextLine()) {
                String[] values = scanner.nextLine().split(" ");
                double value1 = Double.parseDouble(values[2]);
                double value2 = Double.parseDouble(values[3]);
                writer.println("new City(" + value1 + "," + value2 + "),");
            }
            scanner.close();
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }
}
