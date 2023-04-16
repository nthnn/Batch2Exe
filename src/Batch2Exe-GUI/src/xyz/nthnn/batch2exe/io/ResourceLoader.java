package xyz.nthnn.batch2exe.io;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class ResourceLoader {
    public static Image getImageResource(String imageResourceName) {
        try {
            return ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("res/batch2exe-logo.png"));
        }
        catch(IOException ex) { }

        return null;
    }
}
