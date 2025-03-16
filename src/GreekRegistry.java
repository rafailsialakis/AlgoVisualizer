public class GreekRegistry extends CountryRegistry {
    @SuppressWarnings("ALL")
    @Override
    protected void initializeCities() {
        cityNames = new String[]{"Athens", "Thessaloniki","Patra", "Larisa" ,"Volos", "Kavala", "Komotini", "Kozani", "Karditsa", "Tripoli", "Kilkis",
                "Sparti", "Arta", "Kastoria", "Thasos", "Igoumenitsa", "Andros", "Chalkida", "Alexandroupoli", "Kalamata",
                "Xanthi", "Ioannina", "Edessa", "Nafplion", "Orestiada", "Pirgos", "Mesologi", "Katerini", "Grevena", "Veroia", "Lamia", "Korinthos", "Trikala", "Serres",
                "Drama", "Giannitsa", "Florina", "Ptolemaida", "Preveza", "Elassona", "Almiros", "Thiva", "Amfissa", "Argos", "Githieio", "Livadia"};

        cities.add(new City("Athens", 37.9842, 23.7281));
        cities.add(new City("Thessaloniki", 40.6403, 22.9347));
        cities.add(new City("Patra",38.2500,21.7333));
        cities.add(new City("Larisa",39.6417,22.4167));
        cities.add(new City("Volos",39.3667,22.9333));
        cities.add(new City("Kavala",	40.9333,24.4000));
        cities.add(new City("Komotini",41.1219,	25.4042));
        cities.add(new City("Kozani",40.3000,21.7833));
        cities.add(new City("Karditsa",39.3667,	21.9167));
        cities.add(new City("Tripoli",37.5167,	22.3833));
        cities.add(new City("Kilkis",40.9833,	22.8667));
        cities.add(new City("Sparti",37.0739,	22.4294));
        cities.add(new City("Arta",39.1650,20.9875));
        cities.add(new City("Kastoria",40.5167,21.2667));
        cities.add(new City("Thasos",40.7750,24.7083));
        cities.add(new City("Igoumenitsa",39.5034,20.2673));
        cities.add(new City("Andros",37.8158,24.8269));
        cities.add(new City("Chalkida",38.4625,23.5950));
        cities.add(new City("Alexandroupoli", 40.848808,25.873180));
        cities.add(new City("Kalamata",37.027370,22.109119));
        cities.add(new City("Xanthi", 41.135601, 24.883671));
        cities.add(new City("Ioannina", 39.674530, 20.840210));
        cities.add(new City("Edessa",40.802399,22.050690));
        cities.add(new City("Nafplion", 37.568180,22.808661));
        cities.add(new City("Orestiada", 41.506950,26.531530));
        cities.add(new City("Pirgos", 37.672539,21.442490));
        cities.add(new City("Mesologi", 38.368969, 21.430731));
        cities.add(new City("Katerini", 40.271481, 22.508659));
        cities.add(new City("Grevena", 40.083549, 21.425840));
        cities.add(new City("Veroia", 40.526058, 22.202999));
        cities.add(new City("Lamia", 38.899899, 22.433680));
        cities.add(new City("Korinthos", 37.938580, 22.932030));
        cities.add(new City("Trikala", 39.555733, 21.767895));
        cities.add(new City("Serres", 41.088310, 23.542810));
        cities.add(new City("Drama", 41.150051,24.146790));
        cities.add(new City("Giannitsa", 40.793098,22.411501));
        cities.add(new City("Florina",40.782299, 21.409830));
        cities.add(new City("Ptolemaida", 40.511478, 21.679251));
        cities.add(new City("Preveza", 38.960991, 20.750509));
        cities.add(new City("Elassona", 39.894680, 22.188580));
        cities.add(new City("Almiros", 39.182411, 22.758780));
        cities.add(new City("Thiva", 38.319859, 23.322901));
        cities.add(new City("Amfissa", 38.527328, 22.378410));
        cities.add(new City("Argos", 37.635841, 22.729580));
        cities.add(new City("Githieio", 36.759590, 22.565491));
        cities.add(new City("Livadia", 38.436771, 22.874849));

        double minLat = cities.stream().mapToDouble(City::getLatitude).min().getAsDouble();
        double maxLat = cities.stream().mapToDouble(City::getLatitude).max().getAsDouble();
        double minLon = cities.stream().mapToDouble(City::getLongitude).min().getAsDouble();
        double maxLon = cities.stream().mapToDouble(City::getLongitude).max().getAsDouble();

        // Add 10% padding around the min and max coordinates
        double latPadding = (maxLat - minLat) * 0.1;
        double lonPadding = (maxLon - minLon) * 0.1;
        minLat -= latPadding;
        maxLat += latPadding;
        minLon -= lonPadding;
        maxLon += lonPadding;

        double height = 800;
        double width = 800;
        for (City city : cities) {
            // Scale longitude to X coordinate with padding and within 0-799
            double x = ((city.getLongitude() - minLon) / (maxLon - minLon)) * (width - 1);
            // Scale latitude to Y coordinate (inverted) with padding and within 0-799
            double y = (height - 1) - ((city.getLatitude() - minLat) / (maxLat - minLat)) * (height - 1);
            city.setLatitude(x);
            city.setLongitude(y);
        }
    }

    @Override
    protected void initializeConnections() {
        addConnection("Orestiada", "Alexandroupoli", 114, 80);
        addConnection("Alexandroupoli", "Komotini", 61, 50);
        addConnection("Komotini", "Xanthi", 52, 50);
        addConnection("Xanthi", "Kavala", 57, 50);
        addConnection("Kavala", "Thessaloniki", 153, 120);
        addConnection("Thessaloniki", "Veroia", 72, 60);
        addConnection("Thessaloniki", "Giannitsa", 55, 50);
        addConnection("Giannitsa", "Edessa", 50, 40);
        addConnection("Thessaloniki", "Katerini", 73, 60);
        addConnection("Katerini", "Larisa", 84,70);
        addConnection("Veroia", "Kozani", 59, 50);
        addConnection("Larisa", "Volos", 64, 55);
        addConnection("Larisa", "Lamia", 124 , 100);
        addConnection("Lamia", "Chalkida", 167, 130);
        addConnection("Chalkida", "Athens", 78, 60);
        addConnection("Kozani", "Grevena", 47, 30);
        addConnection("Grevena", "Ioannina", 99, 80);
        addConnection("Ioannina", "Igoumenitsa", 79, 60);
        addConnection("Ioannina", "Arta", 76, 60);
        addConnection("Patra", "Pirgos", 97, 80);
        addConnection("Pirgos", "Kalamata", 118, 100);
        addConnection("Kalamata", "Sparti", 99, 80);
        addConnection("Sparti", "Tripoli", 57, 40);
        addConnection("Kalamata", "Tripoli", 82, 70);
        addConnection("Arta", "Mesologi", 110, 100);
        addConnection("Mesologi", "Patra", 49, 40);
        addConnection("Thessaloniki", "Kilkis", 50, 42);
        addConnection("Athens", "Nafplion", 139, 120);
        addConnection("Athens", "Patra", 212, 190);
        addConnection("Nafplion", "Tripoli", 64, 60);
        addConnection("Kozani", "Kastoria", 73, 60);
        addConnection("Grevena", "Kastoria", 73, 60);
        addConnection("Volos", "Lamia", 124, 110);
        addConnection("Karditsa", "Larisa", 80, 70);
        addConnection("Karditsa", "Lamia", 83, 70);
        addConnection("Karditsa", "Ioannina", 160, 150);
        addConnection("Kavala", "Thasos", 96, 90);
        addConnection("Athens", "Andros", 138, 130);
        addConnection("Korinthos", "Tripoli", 79, 70);
        addConnection("Athens", "Korinthos", 83, 80);
        addConnection("Korinthos", "Nafplion", 58, 50);
        addConnection("Patra", "Korinthos", 133, 120);
        addConnection("Trikala", "Larisa", 49, 40);
        addConnection("Trikala", "Karditsa", 32, 30);
        addConnection("Trikala", "Arta", 155, 100);
        addConnection("Serres", "Drama", 70, 65);
        addConnection("Kavala", "Drama", 50, 40);
        addConnection("Serres", "Thessaloniki", 84, 70);
        addConnection("Serres", "Kilkis", 75,60);
        addConnection("Florina", "Kozani", 73, 60);
        addConnection("Florina", "Edessa", 65, 55);
        addConnection("Ptolemaida", "Kozani", 28, 25);
        addConnection("Ptolemaida", "Florina", 56, 50);
        addConnection("Preveza", "Ioannina", 104, 90);
        addConnection("Preveza", "Arta", 60, 50);
        addConnection("Elassona", "Larisa", 42, 35);
        addConnection("Elassona", "Kozani", 58, 50);
        addConnection("Almiros", "Volos", 33, 30);
        addConnection("Almiros", "Larisa", 61, 50);
        addConnection("Githieio", "Sparti", 46, 40);
        addConnection("Githieio", "Kalamata", 117, 100);
        addConnection("Argos", "Tripoli", 55, 45);
        addConnection("Argos", "Korinthos", 40, 35);
        addConnection("Amfissa", "Lamia", 74, 60);
        addConnection("Thiva", "Chalkida", 40, 35);
        addConnection("Thiva", "Athens", 90, 60);
        addConnection("Thiva", "Livadia", 33, 30);
        addConnection("Livadia", "Lamia", 85, 75);
    }

    @Override
    protected String getCountryName() {
        return "Greece";
    }
}