import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonController implements ActionListener {
    private final VisualFrame Frame;
    private final CountryRegistry registry;
    private final JComboBox<String> AlgorithmSelector;  // Declare it here

    // Constructor takes the frame and AlgorithmSelector JComboBox
    public ButtonController(VisualFrame frame, JComboBox<String> AlgorithmSelector, CountryRegistry registry) {
        this.Frame = frame;
        this.AlgorithmSelector = AlgorithmSelector;
        this.registry = registry;
    }

    public void actionPerformed(ActionEvent e) {
        String algorithm = (String) AlgorithmSelector.getSelectedItem();
        String FromCity = Frame.getCityFromField();
        String ToCity = Frame.getCityToField();

        if (algorithm == null || algorithm.isEmpty()) {
            JOptionPane.showMessageDialog(Frame,
                    "Please select a valid algorithm.",
                    "Algorithm not selected",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (registry.findCity(FromCity) == null || registry.findCity(ToCity) == null || registry.findCity(FromCity).equals(registry.findCity(ToCity))) {
            JOptionPane.showMessageDialog(Frame,
                    "Please select valid city names.",
                    "Invalid Cities",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(Frame,
                "Running " + algorithm + " from " + FromCity + " to " + ToCity,
                "Algorithm Selected",
                JOptionPane.INFORMATION_MESSAGE);

        Frame.updateTreePanel(algorithm);
    }


}
