import java.io.File;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class GradientExample {

    public static void main(String[] args) throws Exception {

        int x = 0;
        int y = 0;
        int w = 200;
        int h = 200;
        int r = 80;

        BufferedImage buff = new BufferedImage(200, 200, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = buff.createGraphics();

        g.setColor(Color.PINK);
        g.fillRect(x, y, w, h);

        GradientPaint gradient = new GradientPaint(x, y, Color.RED, w, h, Color.BLUE);
        g.setPaint(gradient);

        g.fillOval(w / 2 - r, h / 2 - r, 2 * r, 2 * r);

        File file = new File("hello.png");
        System.out.printf("Save image to file: %s%n", file);
        ImageIO.write(buff, "png", file);
    }
}

