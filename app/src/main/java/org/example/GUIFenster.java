package org.example;

import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;

public class GUIFenster extends JFrame {
    private Controller con;
    public GUIKomponenten layout;
    public GUIFenster(Controller con) throws MalformedURLException {
        super("Worttrainer");
        this.con = con;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenu text = new JMenu("Welches Wort wird unten dargestellt (Eingabe zum Überprüfen)?");
        JMenuBar normalMenu = new JMenuBar();
        normalMenu.setOpaque(true);
        normalMenu.setPreferredSize(new Dimension(200, 20));
        normalMenu.add(text);

        this.setJMenuBar(normalMenu);
        this.add(layout = new GUIKomponenten(this.con));

        this.pack();
        this.setVisible(true);
    }
}
