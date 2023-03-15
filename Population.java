import java.util.ArrayList;

public class Population{
    ArrayList<Tour> allTours = new ArrayList<Tour>();
    int popCount = Algorithm.popCount;
    int tourSize = Algorithm.tourSize;
    
    //General Population generator to create population of random tours
    public Population(){
        Tour t = new Tour();
        for(int i = 0; i < popCount;i++){
            allTours.add(t.newTour());
        }
    }    

    //Constructor to generate Population from a given tour
    public Population(Tour t){
        for(int i = 0; i < popCount;i++){
            allTours.add(t.newTour());
        }
    }

    //Elitism operator, replaces least fit member of population with input tour 
    public void elitism(Tour t) {
        Tour worst = this.findWorst();
        allTours.remove(worst);
        allTours.add(t);
    }

    //Finds the most fit member of a population
    public Tour getBest(){
        //find worst individual
        double lowest = 999999;
        Tour best = null;
        for(Tour tour:this.allTours){
            if(tour.tourLength < lowest){
                lowest = tour.tourLength;
                best = tour; 
            }
        }
        return best;
    }

    //Finds the least fit member of the population
    public Tour findWorst(){
        //find worst individual
        double highest = 0;
        Tour worst = null;
        for(Tour tour:this.allTours){
            if(tour.tourLength > highest){
                highest = tour.tourLength;
                worst = tour; 
            }
        }
        return worst;
    }

    //toString method to output the whole population
    public String toString(){
        String out = "";
        for(int i = 0; i < popCount; i++){
            out = out.concat(this.allTours.get(i).toString());
            out = out.concat("\n");
        }
        return out;
    }
}