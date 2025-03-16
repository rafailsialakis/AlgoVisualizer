public class GraphEdge {
    private final City FromCity;
    private final City ToCity;
    private final int realDistance;
    private final int straightDistance;

    public GraphEdge(City FromCity, City ToCity, int realDistance, int straightDistance) {
        this.FromCity = FromCity;
        this.ToCity = ToCity;
        this.realDistance = realDistance;
        this.straightDistance = straightDistance;
    }

    public City getFromCity(){
        return FromCity;
    }

    public City getToCity(){
        return ToCity;
    }

    public int getRealDistance(){
        return realDistance;
    }

    public int getStraightDistance(){
        return straightDistance;
    }
}
