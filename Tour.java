/* Code written by Eric-Finley Robins
 * Class represents a single tour of all cities
 */

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.Math;

//Class to represent any tour of a TSP problem set
//Contains utility functions for creating randomised version of the tour from the intital version
public class Tour implements Cloneable, Serializable{
    
    //Variables:
    //Tour size satically loaded from the Population class - must be equal to the length of the tour generated or loaded
    int tourSize = Algorithm.tourSize;
    //Tour Array stores the city coordinated in an array
    City[] tourArray = new City[tourSize];
    //Stores the total distance of the instance of the tour
    double tourLength;

    //Allows for the tour object to be cloned to avoid referencing errors
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    //General contructor insantiating new tour of cities when randomly generated
    public Tour(){
        for(int i=0; i < tourArray.length; i++){
            tourArray[i] = new City();
        }
        tourLength = this.calculateDistance();
    }

    //Contructor used to generate a new tour from a list of cities generated from a previous tour
    public Tour(City[] tourArray){
        this.tourArray = tourArray;
        this.tourLength = this.calculateDistance();
    }

    //Method to serialize a tour object to a file allowing the algorithm to be changed and run on the same TSP set
    public void saveTour(){
        try(FileOutputStream f = new FileOutputStream("file.txt");
            ObjectOutput s = new ObjectOutputStream(f)) {
                s.writeObject(this.tourArray);
            }catch(IOException e){e.printStackTrace();}
    }

    //Reads the serialized tour from file and intantiates it
    public static Tour readTour() throws ClassNotFoundException{
        try(FileInputStream in = new FileInputStream("file.txt");
        ObjectInputStream s = new ObjectInputStream(in)) {
            Tour t = new Tour((City[])s.readObject()); 
            return t;
        }catch(IOException e){e.printStackTrace();
        return null;}
    }

    //Used to find the index of a city from the tourArray
    public int findCity(City c){
        int i = 0;
        for(City c2 : this.tourArray){
            if(c2.equals(c)){
                return i;
            }else{
                i += 1;
            }
        }
        return -1;
    }

    //To generate a new Tour I must withdraw uniformley at random so as to create random permutations
    //This method shuffles an instance of a tour and returns a new one
    public Tour newTour(){
        City[] newTourArray = this.tourArray;
        for(int i=0; i < tourArray.length; i++){
            int index = (int) (Math.random() * newTourArray.length);
            City temp = newTourArray[i];
            newTourArray[i] = newTourArray[index];
            newTourArray[index] = temp; 
        }
        return new Tour(newTourArray.clone());
    }

    //Method to calculate the distance between two cities
    public double euclideanDistance(City city1, City city2){
        double dist = 0;
        Double x1 = city1.getLocation()[0];
        Double y1 = city1.getLocation()[1];
        Double x2 = city2.getLocation()[0];
        Double y2 = city2.getLocation()[1];

        dist = Math.sqrt(Math.pow( x2 - x1, 2) + Math.pow( y2 - y1, 2));
        return dist;
    }

    //Method calls euclidianDistance() on whole tour to calculate the distance
    public double calculateDistance(){
        tourLength = 0;
        for(int i=0; i < tourArray.length -1; i++){
            tourLength += euclideanDistance(tourArray[i], tourArray[i+1]);
        }
        tourLength += euclideanDistance(tourArray[tourSize-1], tourArray[0]);
        return tourLength;
    }

    //Getter for the length of the tour (num of cities)
    public int length() {
        int count = 0;
        for (int i = 0; i < tourArray.length; i++){
            count++;
        }
        return count;
    }

    //Getter for tour distance - string
    public String distanceString(){
        return Double.toString(tourLength);
    }

    //Getter for tour distance - Double
    public double getDistance(){
        return this.tourLength;
    }

    //toString method to output the tour
    public String toString(){
        String tour = "";
        for(City city : tourArray){
            tour = tour.concat(city.toString());
        }
        return tour;
    }
}
