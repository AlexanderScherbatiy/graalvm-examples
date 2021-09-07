import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GenerateSwingImage {

    public static void main(String[] args) throws Exception {

        SwingUtilities.invokeAndWait(() -> {
            JLabel label = new JLabel("Hello, world");
            label.setSize(200, 100);
            BufferedImage buff = new BufferedImage(200, 100, BufferedImage.TYPE_INT_RGB);
            Graphics g = buff.createGraphics();
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, 200, 100);
            label.print(g);
            g.dispose();

            File file = new File("label.png");
            System.out.printf("Save label to image: %s%n", file);

            try {
                ImageIO.write(buff, "png", file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}

