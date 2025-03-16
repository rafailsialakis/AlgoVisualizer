import com.formdev.flatlaf.FlatDarculaLaf;
import javax.swing.*;
import java.awt.*;

/*
AlgoVisualizer, A* and Greedy Search implementation with Graph Visualizer.
@author Rafail Sialakis
 */
public class AlgoVisualizer {
    public static void main(String[] args) {
        InitializeFlatLaf();
        SwingUtilities.invokeLater(VisualFrame::new);
    }

    private static void InitializeFlatLaf(){
        UIManager.put("Panel.background", new Color(45, 45, 45)); // Dark gray background
        UIManager.put("Button.background", new Color(0, 150, 255)); // Blue button background
        UIManager.put("Button.foreground", Color.WHITE); // White button text
        UIManager.put("Button.hoverBackground", new Color(0, 120, 215)); // Darker blue on hover
        try {
            UIManager.setLookAndFeel(new FlatDarculaLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize FlatLaf");
        }
    }
}
