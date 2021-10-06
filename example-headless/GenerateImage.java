import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class GenerateImage {

    public static void main(String[] args) throws Exception {
            BufferedImage buff = new BufferedImage(200, 200, BufferedImage.TYPE_INT_RGB);
            Graphics g = buff.createGraphics();
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, 200, 200);
            g.setColor(Color.BLUE);
            g.drawString("Hello, World!", 50, 100);
            g.dispose();

            File file = new File("hello.png");
            System.out.printf("Save image to file: %s%n", file);

            ImageIO.write(buff, "png", file);
    }
}

