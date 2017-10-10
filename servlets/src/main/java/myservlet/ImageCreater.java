package myservlet;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.Buffer;
import java.util.Random;

public class ImageCreater {

    protected static BufferedImage getBufferedImage() throws IOException {
        BufferedImage bufferedImage = new BufferedImage(640, 120, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = bufferedImage.getGraphics();
        Color color = new Color(new Random().nextInt());
        graphics.setFont(new Font("Arial", Font.BOLD, 72));
        graphics.setColor(color);
        graphics.drawString("Hello world!", 100, 100);
        graphics.dispose();
        return bufferedImage;
    }
}
