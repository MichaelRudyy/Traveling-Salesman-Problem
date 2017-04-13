import java.util.ArrayList;
import java.util.stream.IntStream;

/**
 * Created by Michael Rudyy on 04-Apr-17.
 */
public class Population {
    private ArrayList<Route> routes = new ArrayList<>(Algorithm.POPULATION_SIZE);

    public Population(int populationSize, Algorithm algorithm) {
        IntStream.range(0, populationSize).forEach(x -> routes.add(new Route(algorithm.getInitialRoute())));
    }

    public Population(int populationSize, ArrayList<City> cities) {
        IntStream.range(0, populationSize).forEach(x -> routes.add(new Route(cities)));
    }

    public ArrayList<Route> getRoutes() {
        return routes;
    }

    public void sortRoutesByFitness() {
        routes.sort((route1, route2) -> {
            int flag = 0;
            if (route1.getFitness() > route2.getFitness()) flag = -1;
            else if (route1.getFitness() < route2.getFitness()) flag = 1;
            return flag;
        });
    }
}
