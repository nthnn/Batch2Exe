package xyz.nthnn.batch2exe.io;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class ResourceLoader {
    public static Image getImageResource(String imageResourceName) {
        try {
            return ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream(imageResourceName));
        }
        catch(IOException ex) { }

        return null;
    }

    public static Font getFontResource(String fontResourceName) {
        try {
            return Font.createFont(Font.TRUETYPE_FONT, Thread.currentThread().getContextClassLoader().getResourceAsStream(fontResourceName));
        }
        catch(FontFormatException | IOException ex) { }

        return null;
    }
}
