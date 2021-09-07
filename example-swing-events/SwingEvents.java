import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SwingEvents {

    public static void main(String[] args) throws Exception {

        SwingUtilities.invokeAndWait(() -> {

            JFrame frame = new JFrame("Hello World");

            JPanel panel = new JPanel(new FlowLayout());

            panel.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    System.out.printf("Key pressed: %s%n", e.getKeyChar());
                }

                @Override
                public void keyReleased(KeyEvent e) {
                    System.out.printf("Key released: %s%n", e.getKeyChar());
                }
            });

            panel.addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent e) {
                    System.out.printf("Mouse pressed: [%d, %d]%n", e.getX(), e.getY());
                }
                public void mouseReleased(MouseEvent e) {
                    System.out.printf("Mouse released: [%d, %d]%n", e.getX(), e.getY());
                }
            });

            frame.add(panel);
            frame.setSize(400, 300);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);

            panel.requestFocus();
        });
    }
}

