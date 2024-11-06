package org.example;

import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;
/**
 * Die Klasse GUIFenster erstellt das Hauptfenster der Anwendung für den Worttrainer.
 * @version 1.0
 * @autor davidbednarik
 */



// Die Klasse GUIFenster erstellt das Hauptfenster der Anwendung für den Worttrainer.
public class GUIFenster extends JFrame {
    private Controller con;  // Referenz auf den Controller für die Benutzerinteraktion
    public GUIKomponenten layout;  // Layout-Komponenten, die im Fenster verwendet werden

    // Konstruktor: Initialisiert das Fenster und die Benutzeroberfläche.
    public GUIFenster(Controller con) throws MalformedURLException {
        super("Worttrainer");  // Setzt den Fenstertitel auf "Worttrainer"
        this.con = con;

        // Setzt die Standardaktion beim Schließen des Fensters
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Menüleiste oben im Fenster zur Anzeige der Frage
        JMenu text = new JMenu("Welches Wort wird unten dargestellt (Eingabe zum Überprüfen)?");
        JMenuBar normalMenu = new JMenuBar();
        normalMenu.setOpaque(true);  // Setzt die Menüleiste als undurchsichtig
        normalMenu.setPreferredSize(new Dimension(200, 20));  // Setzt die Größe der Menüleiste
        normalMenu.add(text);  // Fügt das Menü der Menüleiste hinzu

        // Setzt die Menüleiste für das Fenster
        this.setJMenuBar(normalMenu);

        // Fügt die Layout-Komponenten zum Fenster hinzu
        this.add(layout = new GUIKomponenten(this.con));

        // Passt die Fenstergröße so an, dass alle Komponenten Platz finden
        this.pack();

        // Setzt das Fenster sichtbar
        this.setVisible(true);
    }
}
