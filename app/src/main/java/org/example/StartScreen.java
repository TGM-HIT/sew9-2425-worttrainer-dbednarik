package org.example;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class StartScreen extends JFrame
{
    public JButton start;
    public JButton load;
    private JPanel panel;
    private Controller con;
    public StartScreen(Controller con)
    {
        super("Worttrainer");
        this.con = con;

        this.panel = new JPanel();
        this.panel.setLayout(new FlowLayout(FlowLayout.CENTER, 100,67 ));

        this.panel.setPreferredSize(new Dimension(450, 500));

        this.start = new JButton("Start");
        this.start.setPreferredSize(new Dimension(200, 150));
        this.start.addActionListener(this.con);
        this.load = new JButton("Load");
        this.load.setPreferredSize(new Dimension(200, 150));
        this.load.addActionListener(con);
        this.load.setBackground(new Color(47, 210, 22));

        this.panel.add(this.start);
        this.panel.add(this.load);

        this.add(this.panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }
    public String fileAussuchen()
    {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);
        if(result == JFileChooser.APPROVE_OPTION)
        {
            File selectedFile = fileChooser.getSelectedFile();
            return selectedFile.getAbsolutePath();
        }
        return null;
    }
}