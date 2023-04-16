package xyz.nthnn.batch2exe;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.IntelliJTheme;
import xyz.nthnn.batch2exe.io.ResourceLoader;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
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
        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainFrame.setJMenuBar(menuBar);
        this.mainFrame.setResizable(false);

        JLabel logoView = new JLabel(new ImageIcon(ResourceLoader.getImageResource("res/batch2exe-logo.png").getScaledInstance(100, 100, Image.SCALE_REPLICATE)));
        logoView.setBorder(BorderFactory.createEmptyBorder(0, 0, 12, 0));

        JPanel panel0 = new JPanel();
        panel0.add(logoView, Component.CENTER_ALIGNMENT);

        JTextArea fileNameTextArea = new JTextArea();
        fileNameTextArea.setPreferredSize(new Dimension(300, (int) fileNameTextArea.getPreferredSize().getHeight()));

        JButton fileSelectBtn1 = new JButton("Browse");
        fileSelectBtn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileFilter(new FileNameExtensionFilter("Batch File", "bat"));
                fileChooser.setAcceptAllFileFilterUsed(false);
                fileChooser.setApproveButtonMnemonic('O');
                fileChooser.setControlButtonsAreShown(true);
                fileChooser.setDialogTitle("Select batch file to be converted");
                fileChooser.setDragEnabled(true);

                if(fileChooser.showOpenDialog(MainUI.this.mainFrame) == JFileChooser.APPROVE_OPTION)
                    fileNameTextArea.setText(fileChooser.getSelectedFile().toString());
            }
        });

        JPanel panel1 = new JPanel();
        panel1.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
        panel1.add(new JLabel("Batch File"));
        panel1.add(fileNameTextArea);
        panel1.add(fileSelectBtn1);

        JTextArea outputTextArea = new JTextArea();
        outputTextArea.setPreferredSize(new Dimension(300, (int) outputTextArea.getPreferredSize().getHeight()));

        JButton fileSelectBtn2 = new JButton("Browse");
        fileSelectBtn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileFilter(new FileNameExtensionFilter("Executable File", "exe"));
                fileChooser.setAcceptAllFileFilterUsed(false);
                fileChooser.setApproveButtonMnemonic('O');
                fileChooser.setControlButtonsAreShown(true);
                fileChooser.setDialogTitle("Output file name");
                fileChooser.setDragEnabled(true);

                if(fileChooser.showOpenDialog(MainUI.this.mainFrame) == JFileChooser.APPROVE_OPTION)
                    outputTextArea.setText(fileChooser.getSelectedFile().toString());
            }
        });

        JPanel panel2 = new JPanel();
        panel2.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        panel2.add(new JLabel("Output File"));
        panel2.add(outputTextArea);
        panel2.add(fileSelectBtn2);

        JTextArea iconTextArea = new JTextArea();
        iconTextArea.setPreferredSize(new Dimension(300, (int) iconTextArea.getPreferredSize().getHeight()));

        JButton fileSelectBtn3 = new JButton("Browse");
        fileSelectBtn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileFilter(new FileNameExtensionFilter("Icon Files", "ico"));
                fileChooser.setAcceptAllFileFilterUsed(false);
                fileChooser.setApproveButtonMnemonic('O');
                fileChooser.setControlButtonsAreShown(true);
                fileChooser.setDialogTitle("Output icon file name");
                fileChooser.setDragEnabled(true);

                if(fileChooser.showOpenDialog(MainUI.this.mainFrame) == JFileChooser.APPROVE_OPTION)
                    iconTextArea.setText(fileChooser.getSelectedFile().toString());
            }
        });

        JPanel panel3 = new JPanel();
        panel3.setBorder(BorderFactory.createEmptyBorder(0, 13, 0, 0));
        panel3.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        panel3.add(new JLabel("Icon File"));
        panel3.add(iconTextArea);
        panel3.add(fileSelectBtn3);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
        mainPanel.add(panel0);
        mainPanel.add(panel1);
        mainPanel.add(panel2);
        mainPanel.add(panel3);

        JMenuItem fileExit = new JMenuItem("Exit");
        fileExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(JOptionPane.showConfirmDialog(MainUI.this.mainFrame, "Are you sure you want to exit?", "Exit", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION)
                    System.exit(0);
            }
        });

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

        fileMenu.addSeparator();
        fileMenu.add(fileExit);

        aboutMenu.add(aboutDevelopers);
        aboutMenu.add(aboutBatch2Exe);

        this.mainFrame.getContentPane().add(mainPanel);
        this.mainFrame.pack();
        this.mainFrame.setLocationRelativeTo(null);
        this.mainFrame.show();
    }
}