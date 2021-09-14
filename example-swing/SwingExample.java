import java.awt.*;
import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;

public class SwingExample {

    public static void main(String[] args) throws Exception {

        SwingUtilities.invokeAndWait(() -> {

            setNimbusLookAndFeel();

            JFrame frame = new JFrame("Hello World");

            JButton button = new JButton("Hello");
            button.addActionListener(e -> {
                System.out.printf("Hello, World!%n");
            });

            JPanel panel = new JPanel(new FlowLayout());
            panel.add(button);

            frame.add(panel);
            frame.setSize(400, 300);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }

    private static void setNimbusLookAndFeel() {
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

