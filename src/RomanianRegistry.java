public class RomanianRegistry extends CountryRegistry {
    @SuppressWarnings("ALL")
    @Override
    protected void initializeCities() {
        cityNames = new String[]{"Timisoara", "Arad", "Oradea", "Sibiu", "Fagaras",
                "Lugoj", "Mehadia", "Drobeta", "Craiova", "RimnicuVilcea",
                "Pitesti", "Zerind", "Bucharest", "Giurgiu", "Urziceni",
                "Hirsova", "Eforie", "Vasfui", "Iasi", "Neamt"};

        cities.add(new City("Arad", 91, 492));
        cities.add(new City("Zerind", 150, 530));
        cities.add(new City("Timisoara", 50, 400));
        cities.add(new City("Sibiu", 290, 410));
        cities.add(new City("Oradea", 240, 570));
        cities.add(new City("Lugoj", 120, 350));
        cities.add(new City("Mehadia", 160, 300));
        cities.add(new City("Drobeta", 200, 240));
        cities.add(new City("Craiova", 340, 230));
        cities.add(new City("RimnicuVilcea", 380, 360));
        cities.add(new City("Fagaras", 430, 440));
        cities.add(new City("Pitesti", 450, 290));
        cities.add(new City("Bucharest", 580, 340));
        cities.add(new City("Giurgiu", 580, 280));
        cities.add(new City("Urziceni", 680, 370));
        cities.add(new City("Vasfui", 730, 450));
        cities.add(new City("Iasi", 800, 380));
        cities.add(new City("Neamt", 750, 300));
        cities.add(new City("Hirsova", 680, 260));
        cities.add(new City("Eforie", 720, 180));
    }

    @Override
    protected void initializeConnections() {
        addConnection("Timisoara", "Arad", 118, 150);
        addConnection("Arad", "Zerind", 75, 120);
        addConnection("Zerind", "Oradea", 71, 200);
        addConnection("Oradea", "Sibiu", 151, 180);
        addConnection("Sibiu", "Fagaras", 99, 210);
        addConnection("Timisoara", "Lugoj", 111, 190);
        addConnection("Lugoj", "Mehadia", 70, 250);
        addConnection("Mehadia", "Drobeta", 75, 270);
        addConnection("Drobeta", "Craiova", 120, 230);
        addConnection("Craiova", "RimnicuVilcea", 146, 160);
        addConnection("RimnicuVilcea", "Sibiu", 80, 280);
        addConnection("Sibiu", "Fagaras", 97, 150);
        addConnection("Fagaras", "Bucharest", 150, 110);
        addConnection("Pitesti", "Bucharest", 123, 170);
        addConnection("Bucharest", "Giurgiu", 133, 220);
        addConnection("Bucharest", "Urziceni", 145, 210);
        addConnection("Urziceni", "Hirsova", 70, 230);
        addConnection("Urziceni", "Vasfui", 54, 240);
        addConnection("Hirsova", "Eforie", 33, 250);
        addConnection("Vasfui", "Iasi", 55, 180);
        addConnection("Iasi", "Neamt", 69, 300);
    }

    @Override
    protected String getCountryName() {
        return "Romania";
    }
}
