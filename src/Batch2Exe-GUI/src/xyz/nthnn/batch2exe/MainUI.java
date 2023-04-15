package xyz.nthnn.batch2exe;

import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.*;
import java.awt.*;

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
        this.mainFrame.show();
    }
}