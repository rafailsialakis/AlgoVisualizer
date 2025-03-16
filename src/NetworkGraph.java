import edu.uci.ics.jung.algorithms.layout.StaticLayout;
import edu.uci.ics.jung.algorithms.shortestpath.DistanceStatistics;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import org.apache.commons.collections15.Transformer;
import edu.uci.ics.jung.visualization.decorators.EdgeShape;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Map;

public class NetworkGraph extends JPanel {
    private final Transformer<String, Point2D> positionTransformer;

    public NetworkGraph(CountryRegistry registry) {
        setLayout(new BorderLayout());
        UndirectedSparseGraph<String, String> graph = new UndirectedSparseGraph<>();

        // Add nodes and edges
        registry.getCityList().forEach(city -> graph.addVertex(city.getName()));
        registry.getConnectionsList().forEach(edge ->
                graph.addEdge(
                        edge.getFromCity().getName() + "-" + edge.getToCity().getName(),
                        edge.getFromCity().getName(),
                        edge.getToCity().getName()
                )
        );

        // Calculate positions with scaling
        double scale = 0.7;
        Map<String, Point2D> nodePositions = new HashMap<>();
        for (City city : registry.getCityList()) {
            nodePositions.put(
                    city.getName(),
                    new Point2D.Double(city.getLatitude() * scale, city.getLongitude() * scale)
            );
        }

        // Create layout with proper Transformer
        positionTransformer = nodePositions::get;

        StaticLayout<String, String> layout = new StaticLayout<>(
                graph,
                positionTransformer,  // Correct transformer
                new Dimension(800, 800)
        );


        VisualizationViewer<String, String> viewer =
                new VisualizationViewer<>(layout);
        viewer.getRenderContext().setEdgeShapeTransformer(new EdgeShape.Line<>());
        viewer.getRenderContext().setVertexLabelTransformer(v -> "<html><font size='2'>" + v + "</font></html>");
        JTextField diameterField = new JTextField();
        diameterField.setText("Map Of " + registry.getCountryName() + ".  Diameter = " + DistanceStatistics.diameter(graph));
        diameterField.setEditable(false);
        add(viewer, BorderLayout.CENTER);
        add(diameterField, BorderLayout.SOUTH);
    }
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 800); // Match your layout dimensions
    }

    public Transformer<String, Point2D> getNodePositions() {
        return positionTransformer;
    }
}