import java.util.ArrayList;
import java.util.List;

public abstract class CountryRegistry {
    protected final ArrayList<City> cities = new ArrayList<>();
    protected final List<GraphEdge> connections = new ArrayList<>();
    protected String[] cityNames;

    public CountryRegistry() {
        initializeCities();
        initializeConnections();
    }

    // Abstract methods to be implemented by subclasses
    protected abstract void initializeCities();
    protected abstract void initializeConnections();
    protected abstract String getCountryName();

    protected void addConnection(String city1Name, String city2Name, int distance, int straightDistance) {
        City city1 = findCity(city1Name);
        City city2 = findCity(city2Name);
        if (city1 != null && city2 != null) {
            connections.add(new GraphEdge(city1, city2, distance, straightDistance));
        } else {
            System.err.println("Error: One or both cities not found: " + city1Name + ", " + city2Name);
            System.exit(1);
        }
    }

    public City findCity(String name) {
        for (City city : cities) {
            if (city.getName().equals(name)) {
                return city;
            }
        }
        return null;
    }

    public ArrayList<City> getCityList() {
        return cities;
    }

    public List<GraphEdge> getConnectionsList() {
        return connections;
    }

    public String[] getCityNames() {
        return cityNames;
    }

    public ArrayList<City> getNeighbors(City currentCity) {
        ArrayList<City> currentCityConnections = new ArrayList<>();
        for (GraphEdge e : connections) {
            if (e.getToCity().equals(currentCity)) {
                currentCityConnections.add(e.getFromCity());
            } else if (e.getFromCity().equals(currentCity)) {
                currentCityConnections.add(e.getToCity());
            }
        }
        return currentCityConnections;
    }

    public int getDistance(City currentCity, City neighbor) {
        for (GraphEdge e : connections) {
            if ((e.getFromCity().equals(currentCity) && e.getToCity().equals(neighbor)) ||
                    (e.getToCity().equals(currentCity) && e.getFromCity().equals(neighbor))) {
                return e.getRealDistance();
            }
        }
        return 0;
    }

    public int getHeuristic(City currentCity, City neighbor) {
        for (GraphEdge e : connections) {
            if ((e.getFromCity().equals(currentCity) && e.getToCity().equals(neighbor)) ||
                    (e.getToCity().equals(currentCity) && e.getFromCity().equals(neighbor))) {
                return e.getStraightDistance();
            }
        }
        return 0;
    }
}
