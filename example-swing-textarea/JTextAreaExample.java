import java.awt.*;
import javax.swing.*;

public class JTextAreaExample {

    public static void main(String[] args) throws Exception {

        SwingUtilities.invokeAndWait(() -> {

            JFrame frame = new JFrame("Hello World");

            JTextArea textArea = new JTextArea(20, 20);

            JPanel panel = new JPanel(new BorderLayout());
            panel.add(textArea, BorderLayout.CENTER);

            frame.add(panel);
            frame.setSize(400, 300);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}

