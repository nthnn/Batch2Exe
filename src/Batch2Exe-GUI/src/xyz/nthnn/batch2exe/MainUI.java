package xyz.nthnn.batch2exe;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.IntelliJTheme;

import org.json.simple.parser.ParseException;

import xyz.nthnn.batch2exe.core.ProjectInfo;
import xyz.nthnn.batch2exe.io.ConfigIO;
import xyz.nthnn.batch2exe.io.ResourceLoader;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

public class MainUI implements Runnable {
    private JFrame mainFrame;
    private static boolean isSaved = false;

    static {
        IntelliJTheme.ThemeLaf.setup(new FlatDarkLaf());
    }

    @Override
    public void run() {
        DocumentListener docListener = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                MainUI.isSaved = false;
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                MainUI.isSaved = false;
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                MainUI.isSaved = false;
            }
        };
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenu aboutMenu = new JMenu("About");

        menuBar.add(fileMenu);
        menuBar.add(aboutMenu);

        this.mainFrame = new JFrame("Batch2Exe");
        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainFrame.setJMenuBar(menuBar);
        this.mainFrame.setResizable(false);

        JPanel panel0 = new JPanel();
        panel0.add(new JLabel(new ImageIcon(ResourceLoader.getImageResource("res/batch2exe-logo.png").getScaledInstance(100, 100, Image.SCALE_REPLICATE))));

        JLabel titleView = new JLabel("Batch-2-Exe");
        titleView.setFont(ResourceLoader.getFontResource("res/venus-rising.otf").deriveFont(Font.BOLD, 24));
        titleView.setBorder(BorderFactory.createEmptyBorder(0, 0, 12, 0));

        JPanel text0 = new JPanel();
        text0.add(titleView);

        JLabel text1Label = new JLabel("File I/O");
        text1Label.setFont(text1Label.getFont().deriveFont(Font.BOLD, text1Label.getFont().getSize() + 2));

        JPanel text1 = new JPanel();
        text1.setBorder(BorderFactory.createEmptyBorder(14, 0, 0, 0));
        text1.add(text1Label);

        JTextArea fileNameTextArea = new JTextArea();
        fileNameTextArea.setToolTipText("Input batch file name.");
        fileNameTextArea.setPreferredSize(new Dimension(290, (int) fileNameTextArea.getPreferredSize().getHeight()));
        fileNameTextArea.getDocument().addDocumentListener(docListener);

        JButton fileSelectBtn1 = new JButton("Browse");
        fileSelectBtn1.setToolTipText("Browse for input batch file name.");
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

                if(fileChooser.showOpenDialog(MainUI.this.mainFrame) == JFileChooser.APPROVE_OPTION) {
                    fileNameTextArea.setText(fileChooser.getSelectedFile().toString());
                    MainUI.isSaved = false;
                }
            }
        });

        JPanel panel1 = new JPanel();
        panel1.setBorder(BorderFactory.createEmptyBorder(0, 14, 0, 0));
        panel1.add(new JLabel("Batch File"));
        panel1.add(fileNameTextArea);
        panel1.add(fileSelectBtn1);

        JTextArea outputTextArea = new JTextArea();
        outputTextArea.setToolTipText("Output executable file name.");
        outputTextArea.setPreferredSize(new Dimension(290, (int) outputTextArea.getPreferredSize().getHeight()));
        outputTextArea.getDocument().addDocumentListener(docListener);

        JButton fileSelectBtn2 = new JButton("Browse");
        fileSelectBtn2.setToolTipText("Browse for output executable file name.");
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

                if(fileChooser.showOpenDialog(MainUI.this.mainFrame) == JFileChooser.APPROVE_OPTION) {
                    outputTextArea.setText(fileChooser.getSelectedFile().toString());
                    MainUI.isSaved = false;
                }
            }
        });

        JPanel panel2 = new JPanel();
        panel2.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        panel2.setBorder(BorderFactory.createEmptyBorder(0, 9, 0, 0));
        panel2.add(new JLabel("Output File"));
        panel2.add(outputTextArea);
        panel2.add(fileSelectBtn2);

        JTextArea iconTextArea = new JTextArea();
        iconTextArea.setToolTipText("Input icon file name of the executable file output.");
        iconTextArea.setPreferredSize(new Dimension(290, (int) iconTextArea.getPreferredSize().getHeight()));
        iconTextArea.getDocument().addDocumentListener(docListener);

        JButton fileSelectBtn3 = new JButton("Browse");
        fileSelectBtn3.setToolTipText("Browse for input icon file name of the executable file output.");
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

                if(fileChooser.showOpenDialog(MainUI.this.mainFrame) == JFileChooser.APPROVE_OPTION) {
                    iconTextArea.setText(fileChooser.getSelectedFile().toString());
                    MainUI.isSaved = false;
                }
            }
        });

        JPanel panel3 = new JPanel();
        panel3.setBorder(BorderFactory.createEmptyBorder(0, 22, 14, 0));
        panel3.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        panel3.add(new JLabel("Icon File"));
        panel3.add(iconTextArea);
        panel3.add(fileSelectBtn3);

        JLabel text2Label = new JLabel("Runtime Properties");
        text2Label.setFont(text1Label.getFont().deriveFont(Font.BOLD, text2Label.getFont().getSize() + 2));

        JPanel text2 = new JPanel();
        text2.setBorder(BorderFactory.createEmptyBorder(14, 0, 0, 0));
        text2.add(text2Label);

        JTextArea titleArea = new JTextArea();
        titleArea.setToolTipText("Runtime title of the executable file output.");
        titleArea.setPreferredSize(new Dimension(368, (int) titleArea.getPreferredSize().getHeight()));
        titleArea.getDocument().addDocumentListener(docListener);

        JPanel panel4 = new JPanel();
        panel4.setBorder(BorderFactory.createEmptyBorder(0, -4, 0, 0));
        panel4.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        panel4.add(new JLabel("Runtime Title"));
        panel4.add(titleArea);

        JTextArea argsArea = new JTextArea();
        argsArea.setToolTipText("Runtime arguments of the executable file output.");
        argsArea.setPreferredSize(new Dimension(368, (int) titleArea.getPreferredSize().getHeight()));
        argsArea.getDocument().addDocumentListener(docListener);

        JPanel panel5 = new JPanel();
        panel5.setBorder(BorderFactory.createEmptyBorder(0, 9, 0, 0));
        panel5.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        panel5.add(new JLabel("Arguments"));
        panel5.add(argsArea);

        JTextArea workingDirArea = new JTextArea();
        workingDirArea.setToolTipText("Default working directory of the output executable file.");
        workingDirArea.setPreferredSize(new Dimension(290, (int) iconTextArea.getPreferredSize().getHeight()));
        workingDirArea.getDocument().addDocumentListener(docListener);

        JButton fileSelectBtn4 = new JButton("Browse");
        fileSelectBtn4.setToolTipText("Browse for default working directory of the output executable file.");
        fileSelectBtn4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                fileChooser.setAcceptAllFileFilterUsed(false);
                fileChooser.setApproveButtonMnemonic('O');
                fileChooser.setControlButtonsAreShown(true);
                fileChooser.setDialogTitle("Default Working Directory");
                fileChooser.setDragEnabled(true);

                if(fileChooser.showOpenDialog(MainUI.this.mainFrame) == JFileChooser.APPROVE_OPTION) {
                    workingDirArea.setText(fileChooser.getSelectedFile().toString());
                    MainUI.isSaved = false;
                }
            }
        });

        JPanel panel6 = new JPanel();
        panel6.setBorder(BorderFactory.createEmptyBorder(0, 4, 14, 0));
        panel6.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        panel6.add(new JLabel("Working Dir"));
        panel6.add(workingDirArea);
        panel6.add(fileSelectBtn4);

        Runnable saveConfig = new Runnable() {
            @Override
            public void run() {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileFilter(new FileNameExtensionFilter("JSON Files", "json"));
                fileChooser.setAcceptAllFileFilterUsed(false);
                fileChooser.setControlButtonsAreShown(true);
                fileChooser.setDialogTitle("Output config JSON file");
                fileChooser.setDragEnabled(true);

                if(fileChooser.showOpenDialog(MainUI.this.mainFrame) == JFileChooser.APPROVE_OPTION) {
                    try {
                        ConfigIO.save(fileChooser.getSelectedFile().toString(), ProjectInfo.initInfo(titleArea.getText(), fileNameTextArea.getText(), outputTextArea.getText(), iconTextArea.getText(), workingDirArea.getText(), argsArea.getText()));
                        MainUI.isSaved = true;
                    }
                    catch (IOException ex) {
                        JOptionPane.showMessageDialog(mainFrame, "Something went wrong while trying to save the configuration.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        };

        Runnable openConfig = new Runnable() {
            @Override
            public void run() {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileFilter(new FileNameExtensionFilter("JSON Files", "json"));
                fileChooser.setAcceptAllFileFilterUsed(false);
                fileChooser.setControlButtonsAreShown(true);
                fileChooser.setDialogTitle("Input config JSON file");
                fileChooser.setDragEnabled(true);

                if(fileChooser.showOpenDialog(MainUI.this.mainFrame) == JFileChooser.APPROVE_OPTION) {
                    try {
                        ProjectInfo inf = ConfigIO.load(fileChooser.getSelectedFile().toString());
                        fileNameTextArea.setText(inf.fileName);
                        outputTextArea.setText(inf.output);
                        iconTextArea.setText(inf.icon);
                        titleArea.setText(inf.title);
                        argsArea.setText(inf.arguments);
                        workingDirArea.setText(inf.workingDirectory);

                        MainUI.isSaved = true;
                    }
                    catch(IOException ex) {
                        JOptionPane.showMessageDialog(mainFrame, "Something went wrong while trying to open the configuration file.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    catch(ParseException ex) {
                        JOptionPane.showMessageDialog(mainFrame, "Invalid configuration file.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        };

        JButton btnSaveConfig = new JButton("Save Config");
        btnSaveConfig.setToolTipText("Save project configuration.");
        btnSaveConfig.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveConfig.run();
            }
        });

        JButton btnOpenConfig = new JButton("Open Config");
        btnOpenConfig.setToolTipText("Open project configuration.");
        btnOpenConfig.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openConfig.run();
            }
        });

        JPanel dummyPanel = new JPanel();
        dummyPanel.setBorder(BorderFactory.createEmptyBorder(0, 150, 0, 0));

        JButton btnConvert = new JButton("Convert");
        btnConvert.setToolTipText("Convert to executable file.");
        btnConvert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!new File(fileNameTextArea.getText()).exists())
                    JOptionPane.showMessageDialog(mainFrame, "Input batch file not found!", "Error", JOptionPane.ERROR_MESSAGE);

                if(new File(outputTextArea.getText()).exists())
                    JOptionPane.showMessageDialog(mainFrame, "Output executable file already exists!", "Error", JOptionPane.ERROR_MESSAGE);

                if(!new File(iconTextArea.getText()).exists())
                    JOptionPane.showMessageDialog(mainFrame, "Input icon file not found!", "Error", JOptionPane.ERROR_MESSAGE);

                String uuid = UUID.randomUUID().toString();
                try {
                    ConfigIO.save(uuid + ".json", ProjectInfo.initInfo(titleArea.getText(), fileNameTextArea.getText(), outputTextArea.getText(), iconTextArea.getText(), workingDirArea.getText(), argsArea.getText()));
                    if(Runtime.getRuntime().exec("batch2exe-wrapper.exe -c " + uuid + ".json").waitFor() == 2)
                        JOptionPane.showMessageDialog(mainFrame, "Successfully converted!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    else JOptionPane.showMessageDialog(mainFrame, "Something went wrong.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                catch(InterruptedException | IOException ex) {
                    System.err.println(ex.getMessage());
                    JOptionPane.showMessageDialog(mainFrame, "Something went wrong.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                finally {
                    new File(uuid + ".json").delete();
                }
            }
        });

        JPanel panel7 = new JPanel();
        panel7.setBorder(BorderFactory.createEmptyBorder(14, 14, 0, 0));
        panel7.add(btnSaveConfig);
        panel7.add(btnOpenConfig);
        panel7.add(dummyPanel);
        panel7.add(btnConvert);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
        mainPanel.add(panel0);
        mainPanel.add(text0);
        mainPanel.add(new JSeparator());
        mainPanel.add(text1);
        mainPanel.add(panel1);
        mainPanel.add(panel2);
        mainPanel.add(panel3);
        mainPanel.add(new JSeparator());
        mainPanel.add(text2);
        mainPanel.add(panel4);
        mainPanel.add(panel5);
        mainPanel.add(panel6);
        mainPanel.add(new JSeparator());
        mainPanel.add(panel7);

        JMenuItem fileOpenConfig = new JMenuItem("Open");
        fileOpenConfig.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openConfig.run();
            }
        });

        JMenuItem fileSaveConfig = new JMenuItem("Save");
        fileSaveConfig.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveConfig.run();
            }
        });

        JMenuItem fileClose = new JMenuItem("Close");
        fileClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!fileNameTextArea.getText().equals("")
                        || !outputTextArea.getText().equals("")
                        || !iconTextArea.getText().equals("")
                        || !titleArea.getText().equals("")
                        || !argsArea.getText().equals("")
                        || !workingDirArea.getText().equals("")) {
                    if(MainUI.isSaved || JOptionPane.showConfirmDialog(mainFrame, "Are you sure you want to exit? Changes cannot be saved anymore.", "Confirm", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
                        fileNameTextArea.setText("");
                        outputTextArea.setText("");
                        iconTextArea.setText("");
                        titleArea.setText("");
                        argsArea.setText("");
                        workingDirArea.setText("");

                        MainUI.isSaved = false;
                    }
                    else JOptionPane.showMessageDialog(mainFrame, "Configurations cannot be closed.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

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

        JMenuItem aboutGithub = new JMenuItem("GitHub");
        aboutGithub.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI("https://github.com/nthnn/batch2exe"));
                }
                catch(URISyntaxException ex) { }
                catch(IOException ex) {
                    JOptionPane.showMessageDialog(MainUI.this.mainFrame, "Cannot find system's default internet browser.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        fileMenu.add(fileOpenConfig);
        fileMenu.add(fileSaveConfig);
        fileMenu.add(fileClose);
        fileMenu.addSeparator();
        fileMenu.add(fileExit);

        aboutMenu.add(aboutBatch2Exe);
        aboutMenu.add(aboutDevelopers);
        aboutMenu.addSeparator();
        aboutMenu.add(aboutGithub);

        this.mainFrame.getContentPane().add(mainPanel);
        this.mainFrame.pack();
        this.mainFrame.setLocationRelativeTo(null);
        this.mainFrame.show();
    }
}