package xyz.nthnn.batch2exe;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.IntelliJTheme;
import xyz.nthnn.batch2exe.io.ResourceLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainUI implements Runnable {
    private JFrame mainFrame;

    static {
        IntelliJTheme.ThemeLaf.setup(new FlatDarkLaf());
    }

    @Override
    public void run() {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenu aboutMenu = new JMenu("About");

        menuBar.add(fileMenu);
        menuBar.add(aboutMenu);

        this.mainFrame = new JFrame("Batch2Exe");
        this.mainFrame.setSize(new Dimension(420, 510));
        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainFrame.setLocationRelativeTo(null);
        this.mainFrame.setJMenuBar(menuBar);
        this.mainFrame.setIconImage(ResourceLoader.getImageResource("res/batch2exe-logo.ico"));

        JMenuItem aboutDevelopers = new JMenuItem("Developers");
        aboutDevelopers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(MainUI.this.mainFrame, "Leader:\r\nBaldivicio, Jonathan Eldy I.\r\n\r\nMembers:\r\nDela Pena, Jefford\r\nEvaristo, Argie\r\nPago, Jhondel\r\nReyes, Damiel", "Developers", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        JMenuItem aboutBatch2Exe = new JMenuItem("Batch2Exe");
        aboutBatch2Exe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(MainUI.this.mainFrame, "Batch2Exe is a program for converting batch files to executable files on Windows. It has a user-friendly interface for inputting information and uses advanced compression and obfuscation techniques for security.", "About", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        aboutMenu.add(aboutDevelopers);
        aboutMenu.add(aboutBatch2Exe);

        this.mainFrame.show();
    }
}