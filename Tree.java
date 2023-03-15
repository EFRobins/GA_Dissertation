import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

//Helper class to load and save a tour generated from TSP problem set file from TSP lib
//License under CC BY-SA 4.0 
//Taken and adapted from URL: "https://stackoverflow.com/questions/52663797/trouble-with-scanner-cant-manage-to-read-in-tsp-file"
public class Tree {
    ArrayList<String[]> storing;

    public Tree() throws Exception {
        File file = new File("C:/Users/robin/Documents/Uni/Dissertation/burma14.tsp");
        Scanner sc = new Scanner(file);
        storing = new ArrayList<String[]>();
        String nextValue = null;
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if("NODE_COORD_SECTION".equals(line)){
                while (sc.hasNextLine()) {
                    nextValue = sc.nextLine();
                    storing.add(nextValue.trim().split(" "));
                }
            }
        }
        sc.close();
    }

    public static ArrayList<String[]> returnScanner() throws Exception {
        Tree tree = new Tree();
        return tree.storing;
    }

    public static Boolean contains(String[] list, String s){
        Boolean result = false;
        for(int i = 0; i < list.length; i++){
            if(list[i] == s){result = true;}
        }
        return result;
    }


    public static void main(String[] args) throws Exception {
        ArrayList<String[]> storedValues = returnScanner();
        for(int i = 0; i < storedValues.size()-5; i++) {
            String[] line = storedValues.get(i);
            System.out.println(line[0]);
        }
 
        City[] tourToSave = new City[storedValues.size()-5];  
        for(int i = 0; i < storedValues.size()-5; i++) {
            String[] line = storedValues.get(i);
            Double x = Double.parseDouble(line[1]);
            Double y = Double.parseDouble(line[2]);
            City c = new City(x,y);
            tourToSave[i] = c;
            System.out.println(line[2]);
        }

         
        Tour t = new Tour(tourToSave);
        System.out.println(t.toString());
        t.saveTour();

    }
}
