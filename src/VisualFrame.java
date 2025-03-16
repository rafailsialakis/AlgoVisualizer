import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Objects;

/*
VisualFrame is the Frame of the App. It is composed by 4 Main Panels:
A Front Panel that has the JLabels and the JComboBoxes
A ButtonPanel that has the JButton and the JComboBox for algorithm selection
A NetworkGraph Panel that shows the graph of the network
A TreeVisual Panel that shows the graph of the shortest path of the selected cities using the selected algorithm
 */

public class VisualFrame extends JFrame {
    private final JComboBox<String> CityFromField;
    private final JComboBox<String> CityToField;

    private final JButton ShortestPathCalculator = new JButton("Calculate Shortest Path");
    private final JComboBox<String> AlgorithmSelector = new JComboBox<>(new String[]{"", "A*", "Best"});

    private final JPanel WorkPanel = new JPanel(new BorderLayout());
    private final JPanel FrontPanel = new JPanel(new GridLayout(2,2));
    private final JPanel ButtonPanel = new JPanel();
    private final TreeVisual TreePanel = new TreeVisual();
    private CountryRegistry registry;

    public VisualFrame(){
        String[] countries = {"Romania", "Greece"};
        registry = null;
        String selectedCountry = (String) JOptionPane.showInputDialog(
                this,
                "Select a country:",
                "Country Selection",
                JOptionPane.QUESTION_MESSAGE,
                null,
                countries,
                countries[0]  // Default selection
        );
        switch (selectedCountry){
            case "Romania":
                registry = new RomanianRegistry();
                break;
            case "Greece":
                registry = new GreekRegistry();
                break;
            case null:
                System.exit(1);
            default:
                throw new IllegalStateException("Unexpected value: " + selectedCountry);
        }
        CityFromField = new JComboBox<>(registry.getCityNames());
        CityToField = new JComboBox<>(registry.getCityNames());
        initializeJComboBoxes();

        JPanel panel = new JPanel(new BorderLayout());
        this.setContentPane(panel);

        this.setVisible(true);
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.setTitle("AlgoVisualizer");
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        //Use of a WindowListener to check if the user is sure to exit.
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int choice = JOptionPane.showConfirmDialog(
                        VisualFrame.this,
                        "Are you sure you want to exit?",
                        "Confirm Exit",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE
                );
                if (choice == JOptionPane.YES_OPTION) {
                    System.exit(0); // Exit application
                }
            }
        });

        this.setLocationRelativeTo(null);

        ShortestPathCalculator.addActionListener(new ButtonController(this, AlgorithmSelector, registry));

        InitializeFrontPanel();

        panel.add(FrontPanel, BorderLayout.NORTH);
        panel.add(ButtonPanel);
        panel.add(WorkPanel, BorderLayout.SOUTH);

        NetworkGraph graphPanel = new NetworkGraph(registry);
        WorkPanel.add(graphPanel, BorderLayout.WEST);
    }

    private void InitializeFrontPanel(){
        FrontPanel.add(new JLabel("Starting Point"));
        FrontPanel.add(new JLabel("Ending Point"));

        FrontPanel.add(CityFromField);
        FrontPanel.add(CityToField);

        ButtonPanel.add(AlgorithmSelector);
        ButtonPanel.add(ShortestPathCalculator);
    }

    public String getCityFromField() {
        return Objects.requireNonNull(CityFromField.getSelectedItem()).toString();
    }

    public String getCityToField() {
        return Objects.requireNonNull(CityToField.getSelectedItem()).toString();
    }

    // In VisualFrame.java
    public void updateTreePanel(String algorithm) {
        WorkPanel.remove(TreePanel);
        TreeVisual newTreePanel = new TreeVisual(
                getCityFromField(),
                getCityToField(),
                algorithm,
                registry
        );
        WorkPanel.add(newTreePanel, BorderLayout.EAST);
        WorkPanel.revalidate();
        WorkPanel.repaint();
    }

    private void initializeJComboBoxes(){
        CityFromField.insertItemAt("", 0);
        CityFromField.setSelectedIndex(0);
        CityToField.insertItemAt("", 0);
        CityToField.setSelectedIndex(0);
    }
}
