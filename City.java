/* Code written by Eric-Finley Robins
 * Class represents a single city in a tour
 */

import java.util.Random;
import java.io.Serializable;

//Class definition for the a City object
public class City implements Serializable{
    //Attributes for coordinates and array they are stored in
    Double x;
    Double y;
    Double[] location = new Double[2];
    //Bound for the coordinates when generating random cities
    int bound = 100;

    //constructor to generate city location in a 100x100 grid
    public City(){
        Random rand = new Random();
        Double x = rand.nextDouble() * bound;
        Double y = rand.nextDouble() * bound;
        x += 1;
        y += 1;
        location[0] = x;
        location[1] = y;
    }

    //Contructor to generate a City instance from given coordinates
    public City(Double x, Double y){
        location[0] = x;
        location[1] = y;
    }

    public Double getValue1(){
        return this.x;
    }

    public Double getValue2(){
        return this.y;
    }

    //Getter for city coordinates
    public Double[] getLocation(){
        return this.location;
    }

    //City toString method
    public String toString() {
        return ("[" + Double.toString(location[0]) + "," + Double.toString(location[1])+ "] ");
    }
}