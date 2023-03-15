import org.jfree.data.*;

import javafx.scene.chart.XYChart.Data;

public class Algorithm{

    public static final int popCount = 500;
    static Tour t = new Tour(testTours.ulysses16);
    public static final int tourSize = 16;
    public static Population p = new Population(t); 

    public static void main(String[] args) throws ClassNotFoundException{

        /*
        try{
        Tour n = new Tour();
        n.saveTour();
        Tour.readTour();
        }catch (Exception e){System.out.println("failed");}
         */

        int generations = 0; 
        while(generations < 100001){
            
            Tour parent1 = Operators.inverseTournamentSelection(p);
            Tour parent2 = Operators.inverseTournamentSelection(p);
            //System.out.println(parent1);

            Tour child = Operators.GSX1(parent1, parent2);
            child = Operators.standardBitMutation(child);
            p.elitism(child); 

            if(generations % 100 == 0){
                System.out.print("Best value at generation" + " " + generations + ": ");
                System.out.println(p.getBest().tourLength);
                System.out.println(p.getBest());
            }
            generations++;
        }


        //Positive Tournament GSX1
        /*
         * int generations = 0; 
        while(generations < 1000001){
            
            Tour parent1 = Operators.positiveTournamentSelection(p);
            Tour parent2 = Operators.positiveTournamentSelection(p);
            //System.out.println(parent1);

            Tour child = Operators.GSX1(parent1, parent2);
            child = Operators.standardBitMutation(child);
            p.elitism(child); 

            if(generations % 10000 == 0){
                System.out.print("Best value at generation" + " " + generations + ": ");
                System.out.println(p.getBest().calculateDistance());
            }
            generations++;
        }
         */

        //Proportionate Selection GSX1
        /*
         * int generations = 0; 
        while(generations < 1000001){
            
            Tour parent1 = Operators.positiveTournamentSelection(p);
            Tour parent2 = Operators.positiveTournamentSelection(p);
            //System.out.println(parent1);

            Tour child = Operators.GSX1(parent1, parent2);
            child = Operators.standardBitMutation(child);
            p.elitism(child); 

            if(generations % 10000 == 0){
                System.out.print("Best value at generation" + " " + generations + ": ");
                System.out.println(p.getBest().calculateDistance());
            }
            generations++;
        }
         */
    }
}
