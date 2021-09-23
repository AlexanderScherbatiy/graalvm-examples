import java.awt.*;
import javax.swing.*;

public class JTextFieldExample {

    public static void main(String[] args) throws Exception {

        SwingUtilities.invokeAndWait(() -> {
            createAndShowGUI();
        });
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Hello World");

        JTextField textField = new JTextField();

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(textField, BorderLayout.CENTER);

        frame.add(panel);
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

