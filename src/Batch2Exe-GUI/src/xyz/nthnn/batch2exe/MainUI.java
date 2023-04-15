package xyz.nthnn.batch2exe;

import com.formdev.flatlaf.FlatDarkLaf;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MainUI implements Runnable {
    private JFrame mainFrame;

    static {
        FlatDarkLaf.setup();
    }

    @Override
    public void run() {
        this.mainFrame = new JFrame("Batch2Exe");
        this.mainFrame.setSize(new Dimension(300, 300));
        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainFrame.setLocationRelativeTo(null);

        try {
            this.mainFrame.setIconImage(ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("res/batch2exe-logo.png")));
        }
        catch(IOException ex) { }

        this.mainFrame.show();
    }
}