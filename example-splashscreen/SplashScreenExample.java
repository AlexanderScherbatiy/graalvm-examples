import javax.swing.*;
import java.awt.*;

public class SplashScreenExample {

    public static void main(String[] args) throws Exception {

        SplashScreen splashScreen = SplashScreen.getSplashScreen();
        if (splashScreen == null) {
            System.out.printf("SplashScreen is null!%n");
            return;
        }

        Graphics2D g = splashScreen.createGraphics();
        Font font = g.getFont();
        g.setFont(font.deriveFont(font.getSize() + 5.0f));
        Rectangle bounds = splashScreen.getBounds();
        for (int i = 0; i < 5; i++) {
            drawSplash(g, bounds, i * 20);
            splashScreen.update();
            Thread.sleep(800);
        }

        splashScreen.close();

        SwingUtilities.invokeAndWait(() -> {
            createAndShowGUI();
        });
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Splash Screen Example");
        frame.getContentPane().add(new JLabel("Splash Screen Example"));
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private static void drawSplash(Graphics2D g, Rectangle bounds, int step) {
        int w = bounds.width;
        int h = bounds.height;
        int r = 80;
        g.setColor(Color.YELLOW);
        g.fillOval(w / 2 - r, h / 2 - r, 2 * r, 2 * r);
        g.setColor(Color.BLUE);
        String text = String.format("SplashScreen: %d%%", step);
        g.drawString(text, w / 2 - 75, h / 2);
    }
}

