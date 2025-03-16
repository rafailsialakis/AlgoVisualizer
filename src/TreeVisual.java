import edu.uci.ics.jung.algorithms.layout.StaticLayout;
import edu.uci.ics.jung.algorithms.shortestpath.DistanceStatistics;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.decorators.EdgeShape;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import org.apache.commons.collections15.Transformer;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.awt.geom.Point2D;


public class TreeVisual extends JPanel {
    private NetworkGraph graph;

    public TreeVisual(){} //Empty constructor to init panel in
    public TreeVisual(String c1, String c2, String algorithm, CountryRegistry registry){
        setLayout(new BorderLayout());
        City fromCity = registry.findCity(c1);
        City toCity = registry.findCity(c2);
        List<City> Result = new ArrayList<>();
        if(algorithm.equals("A*")){
            Result = (new Algorithms(registry).Astar(fromCity, toCity));
        } else if (algorithm.equals("Best")) {
            Result = (new Algorithms(registry).bestFirstSearch(fromCity, toCity));
        }
        UndirectedSparseGraph<String, String> treeGraph = new UndirectedSparseGraph<>();
        for(City c: Result){
            treeGraph.addVertex(c.getName());
        }
        AtomicInteger cID = new AtomicInteger();
        for(int i = 0; i < Result.size() - 1; i++){
            treeGraph.addEdge(String.valueOf(cID.incrementAndGet()), Result.get(i).getName(), Result.get(i+1).getName());
        }

        JTextField diameterField = new JTextField();
        diameterField.setEditable(false);
        diameterField.setText("Shortest path between " + fromCity.getName() + " and " + toCity.getName() + " using " + algorithm + ". Distance = "  + calculateTotalDistance(Result, registry) + "km. Diameter = " + DistanceStatistics.diameter(treeGraph));
        Transformer<String, Point2D> positionTransformer = new NetworkGraph(registry).getNodePositions();
        StaticLayout<String, String> layout = new StaticLayout<>(
                treeGraph,
                positionTransformer,
                new Dimension(800,800)
        );        VisualizationViewer<String,String> Viewer = new VisualizationViewer<>(layout);
        Viewer.getRenderContext().setEdgeShapeTransformer(new EdgeShape.Line<>());
        Viewer.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<>());
        this.add(Viewer, BorderLayout.NORTH);
        this.add(diameterField, BorderLayout.SOUTH);
    }

    private int calculateTotalDistance(List<City> result, CountryRegistry registry){
        int totalDistance = 0;
        List<GraphEdge> Connections = registry.getConnectionsList();
        for(int i = 0; i < result.size() - 1; i++){
            for(GraphEdge edge: Connections){
                if ((result.get(i).equals(edge.getFromCity()) && result.get(i+1).equals(edge.getToCity())) ||
                        (result.get(i).equals(edge.getToCity()) && result.get(i+1).equals(edge.getFromCity()))) {
                    totalDistance += edge.getRealDistance();
                    break;
                }
            }
        }
        return totalDistance;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 800); // Match your layout dimensions
    }
}
