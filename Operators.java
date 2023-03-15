import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Operators {
    static int tSize = 5;
    static Random rand = new Random();
    
    //Inverse tournament selection operator
    public static Tour inverseTournamentSelection(Population population){
        
        final int size = Algorithm.tourSize;
        Tour[] tournament = new Tour[tSize];
        //randomly select tournament of size specified
        int i = 0;
        while(i < tSize){
            int index = rand.nextInt(size);
            tournament[i] = population.allTours.get(index);
            i++;
        }

        //find worst individual
        double highest = 0;
        Tour worst = null;
        for(Tour tour:tournament){
            if(tour.tourLength > highest){
                highest = tour.tourLength;
                worst = tour; 
            }
        }
        return worst;
    }

    //Positive rank tournament selection
    public static Tour positiveTournamentSelection(Population population){
        
        final int size = Algorithm.tourSize;
        Tour[] tournament = new Tour[tSize];
        //randomly select tournament of size specified
        int i = 0;
        while(i < tSize){
            int index = rand.nextInt(size-1);
            tournament[i] = population.allTours.get(index);
            i++;
        }

        //find worst individual
        double lowest = 99999;
        Tour best = null;
        for(Tour tour:tournament){
            if(tour.tourLength < lowest){
                lowest = tour.tourLength;
                best = tour; 
            }
        }
        return best;
    }

    //Proportionate selection operator
    public static Tour proportionateSelection(Population population){
        Double total_fitness = 0.0;
        for(Tour t: population.allTours){
            total_fitness += 1 / t.getDistance();
        }

        double propValue = Math.random() * total_fitness;
        double zero = 0.0;

        for(Tour t: population.allTours){
            propValue -= 1 / t.getDistance();
            if(propValue <= zero){
                return t;   
            }
        }
        return null;
    }

    //Uniform random selection
    public static Tour uniformSelection(Population population){
        int index = rand.nextInt(Algorithm.tourSize - 1);
        return population.allTours.get(index);        
    }

    //Method to check if tour(partial or full) contains a city - used in GSX operators
    public static Boolean containsCity(City[] tour, City cityToCheck){
        Boolean result = false;
        for(int i = 0; i < tour.length; i++){
            try{
                if(tour[i].equals(cityToCheck)){
                    result = true;
                }}catch(NullPointerException e){
                    continue;
                }
        }
        return result;
    }

    //GSX-0 operator
    public static Tour GSX(Tour t1, Tour t2){
        Double ind = Math.floor(Math.random() * t1.length());
        int index1 = ind.intValue();
        City crossoverCity = t1.tourArray[index1];
        int index2 = t2.findCity(crossoverCity);
        
        City[] c = new City[t1.tourSize];

        int halfway = t1.tourSize/2;
        c[halfway] = crossoverCity;
        Boolean cityNotFound = true;
        
     
        int place1 = halfway+1;
        int place2 = halfway-1;
        index1 += 1;
        index2 -= 1;
        
        //Initial Loop, adding cities in alternate directions from the crossover city
        int count = 0;
        while(cityNotFound){
            if(count==0){

                if(index1 == t1.tourSize){index1 = 0;}
                if(place1 == t1.tourSize){place1 = 0;}
                if(index2 == -1){index2 = t1.tourSize - 1;} 
                if(place2 == -1){place2 = t1.tourSize - 1;}   
                
                if( containsCity(c,t1.tourArray[index1]) ){
                    break;
                }else{
                    c[place1] = t1.tourArray[index1]; 
                }
                if( containsCity(c,t2.tourArray[index2]) ){
                    break;
                }else{
                    c[place2] = t2.tourArray[index2];
                }
                
                index1 += 1;
                index2 -= 1;

                place1 += 1;
                place2 -= 1;
            
                count+=1;
                continue;
            }
            if(index1 == t1.tourSize){index1 = 0;}
            if(place1 == t1.tourSize){place1 = 0;}
            if(index2 == -1){index2 = t1.tourSize - 1;} 
            if(place2 == -1){place2 = t1.tourSize - 1;}   
            
            if( containsCity(c,t1.tourArray[index1]) ){
                break;
            }else{
                c[place1] = t1.tourArray[index1];}
            if( containsCity(c,t2.tourArray[index2]) ){
                break;
            }else{
                c[place2] = t2.tourArray[index2];}

            index1 += 1;
            index2 -= 1;
            place1 += 1;
            place2 -= 1;

        }

        //Finding places in tour left to fill
        ArrayList<Integer> nullIndexes = new ArrayList<Integer>();
        for(int i=0;i<t1.tourSize;i++){
            if(c[i] == null){
                nullIndexes.add(i);
            }
        }
        ArrayList<City> remainingCities = new ArrayList<City>();
        for(int i=0;i<t1.tourSize;i++){
            if(!containsCity(c, t1.tourArray[i])){
                remainingCities.add(t1.tourArray[i]);
            }
        }

        Collections.shuffle(nullIndexes);    

        for(int i=0;i<remainingCities.size();i++){
            c[nullIndexes.get(i)] = remainingCities.get(i);
        }
        
        Tour crossed = new Tour(c);
        return crossed;
    }   

    //GSX-1 operator
    public static Tour GSX1(Tour t1, Tour t2){
        Double ind = Math.floor(Math.random() * t1.length());
        int index1 = ind.intValue();
        City crossoverCity = t1.tourArray[index1];
        int index2 = t2.findCity(crossoverCity);
        
        City[] c = new City[t1.tourSize];

        int halfway = t1.tourSize/2;
        c[halfway] = crossoverCity;
        Boolean cityNotFound = true;
        
        int place1 = halfway+1;
        int place2 = halfway-1;
        index1 += 1;
        index2 -= 1;
        
        //Initial Loop, adding cities in alternate directions from the crossover city
        int count = 0;
        while(cityNotFound){
            if(count==0){

                if(index1 == t1.tourSize){index1 = 0;}
                if(place1 == t1.tourSize){place1 = 0;}
                if(index2 == -1){index2 = t1.tourSize - 1;} 
                if(place2 == -1){place2 = t1.tourSize - 1;}   
                
                if( containsCity(c,t1.tourArray[index1]) ){
                    break;
                }else{
                    c[place1] = t1.tourArray[index1]; 
                }
                if( containsCity(c,t2.tourArray[index2]) ){
                    break;
                }else{
                    c[place2] = t2.tourArray[index2];
                }
                
                index1 += 1;
                index2 -= 1;

                place1 += 1;
                place2 -= 1;
            
                count+=1;
                continue;
            }
            if(index1 == t1.tourSize){index1 = 0;}
            if(place1 == t1.tourSize){place1 = 0;}
            if(index2 == -1){index2 = t1.tourSize - 1;} 
            if(place2 == -1){place2 = t1.tourSize - 1;}   
            
            if( containsCity(c,t1.tourArray[index1]) ){
                break;
            }else{
                c[place1] = t1.tourArray[index1];}
            if( containsCity(c,t2.tourArray[index2]) ){
                break;
            }else{
                c[place2] = t2.tourArray[index2];}

            index1 += 1;
            index2 -= 1;
            place1 += 1;
            place2 -= 1;

        }

        //Finding places in tour left to fill
        ArrayList<Integer> nullIndexes = new ArrayList<Integer>();
        for(int i=0;i<t1.tourSize;i++){
            if(c[i] == null){
                nullIndexes.add(i);
            }
        }
        ArrayList<City> remainingCities = new ArrayList<City>();
        for(int i=0;i<t1.tourSize;i++){
            if(!containsCity(c, t2.tourArray[i])){
                remainingCities.add(t2.tourArray[i]);
            }
        }

        for(int i=0;i<remainingCities.size();i++){
            c[nullIndexes.get(i)] = remainingCities.get(i); 
        }

        Tour crossed = new Tour(c);
        return crossed;
    }

    //Standard bit mutation - swaps 2 cities with probability 1/tourSize 
    public static Tour standardBitMutation(Tour t){
        City[] array = t.tourArray.clone();
        int prob = rand.nextInt(array.length);

        for(int i = 0; i < array.length; i++){
            if(prob == array.length){
                Double city2D = Math.floor(Math.random() * array.length);
                int city2 = city2D.intValue();
                City temp = array[i];
                array[i] = array[city2];
                array[city2] = temp;
            }
        }
        return new Tour(array);
    }
}
