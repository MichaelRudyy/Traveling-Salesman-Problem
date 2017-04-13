import com.sun.org.apache.xpath.internal.SourceTree;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Michael Rudyy on 04-Apr-17.
 */
public class Driver {
    public ArrayList<City> initialRoute = new ArrayList<City>(Arrays.asList(
            new City("Boston", 42.3601, -71.0589),
            new City("Houston", 29.7604, -95.3698),
            new City("Austin", 30.2672, -97.7431),
            new City("San Francisco", 37.7749, -122.4749),
            new City("Denver", 39.7392, -104.9903),
            new City("Los Angeles", 34.0522, -118.2437),
            new City("Chicago", 41.8781, -87.6298),
            new City("New York", 40.7128, -74.0059),
            new City("Dallas", 32.7767, -96.7970),
            new City("Seattle", 47.6062, -122.3321)
    ));

    public static void main(String[] args) {
        Driver driver = new Driver();
        Population population = new Population(Algorithm.POPULATION_SIZE, driver.initialRoute);
        population.sortRoutesByFitness();
        Algorithm algorithm = new Algorithm(driver.initialRoute);
        int generationNumber = 0;
        driver.printHeading(generationNumber++);
        driver.printPopulation(population);

        for (int i = 0 ; i <population.getRoutes().size() ; i++){
            System.out.println(i + " / " + population.getRoutes().get(i).toString());
        }


        while (generationNumber < algorithm.NUMB_OF_GENERATION){
            driver.printHeading(generationNumber++);
            population = algorithm.evolve(population);
            population.sortRoutesByFitness();
            driver.printPopulation(population);
        }
        System.out.println(" BEST ROAD : " + population.getRoutes().get(0));
        System.out.println(" w/ a distance of " + String.format("%.2f",population.getRoutes().get(0).calculateTotalDistance())+" mm");
    }

    public void printPopulation(Population population) {
        population.getRoutes().forEach(x -> {
            System.out.println(Arrays.toString(x.getCities().toArray()) + " | " + String.format("%.4f ", x.getFitness()) + "  |  " + String.format("%.2f", x.calculateTotalDistance()));
        });
        System.out.println("");
    }

    public void printHeading(int generationNumber) {
        System.out.println(" > Generation #" + generationNumber);
        String headingColumn1 = "Route";
        String remainingHeadingColums = "Fitness  |  Distance";
        int cityNameLength = 0;
        for (int x = 0; x < initialRoute.size(); x++) {
            cityNameLength += initialRoute.get(x).getName().length();
        }
        int arrayLength = cityNameLength + initialRoute.size() * 2;
        int partialLength = (arrayLength - headingColumn1.length()) / 2;
        for (int x = 0; x < partialLength; x++) {
            System.out.print(" ");
        }
        System.out.print(headingColumn1);
        for (int x = 0; x < partialLength; x++) {
            System.out.print(" ");
        }
        if ((arrayLength % 2) == 0) {
            System.out.print(" ");
        }
        if ((arrayLength % 2) == 0) {
            System.out.println(" ");
        }
        System.out.println(" | " + remainingHeadingColums);
        cityNameLength += remainingHeadingColums.length() + 3;
        for (int x = 0; x < cityNameLength + initialRoute.size() * 2; x++) {
            System.out.print("-");
        }
        System.out.println("");
    }

}