package org.example;

import javax.swing.*;
import java.awt.*;
import java.io.File;

// Die Klasse StartScreen erstellt den Startbildschirm der Anwendung Worttrainer.
public class StartScreen extends JFrame {
    public JButton start;  // Button zum Starten eines neuen Spiels
    public JButton load;  // Button zum Laden eines gespeicherten Spiels
    private JPanel panel;  // Panel, um die Buttons zu platzieren
    private Controller con;  // Referenz auf den Controller für die Benutzerinteraktion

    // Konstruktor: Initialisiert den Startbildschirm
    public StartScreen(Controller con) {
        super("Worttrainer");  // Setzt den Fenstertitel auf "Worttrainer"
        this.con = con;

        // Panel für die Start- und Lade-Buttons
        this.panel = new JPanel();
        this.panel.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 67));  // Setzt das Layout auf FlowLayout

        this.panel.setPreferredSize(new Dimension(450, 500));  // Setzt die bevorzugte Größe des Panels

        // Erstelle den "Start"-Button
        this.start = new JButton("Start");
        this.start.setPreferredSize(new Dimension(200, 150));  // Setzt die Größe des Buttons
        this.start.addActionListener(this.con);  // Fügt einen ActionListener hinzu, um auf Klicks zu reagieren

        // Erstelle den "Load"-Button
        this.load = new JButton("Load");
        this.load.setPreferredSize(new Dimension(200, 150));  // Setzt die Größe des Buttons
        this.load.addActionListener(con);  // Fügt einen ActionListener hinzu, um auf Klicks zu reagieren
        this.load.setBackground(new Color(47, 210, 22));  // Setzt die Hintergrundfarbe des Buttons

        // Füge die Buttons dem Panel hinzu
        this.panel.add(this.start);
        this.panel.add(this.load);

        // Füge das Panel dem JFrame hinzu
        this.add(this.panel);

        // Setze die Standardaktion beim Schließen des Fensters
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Passt die Fenstergröße an, um alle Komponenten aufzunehmen
        this.pack();

        // Setzt das Fenster sichtbar
        this.setVisible(true);
    }

    // Methode, um eine Datei auszuwählen (zum Laden eines gespeicherten Spiels)
    public String fileAussuchen() {
        JFileChooser fileChooser = new JFileChooser();  // Erstelle einen Datei-Auswahldialog
        int result = fileChooser.showOpenDialog(this);  // Öffne den Dialog und speichere das Ergebnis
        if (result == JFileChooser.APPROVE_OPTION) {  // Prüfe, ob eine Datei ausgewählt wurde
            File selectedFile = fileChooser.getSelectedFile();  // Hole die ausgewählte Datei
            return selectedFile.getAbsolutePath();  // Gebe den absoluten Pfad der Datei zurück
        }
        return null;  // Gebe null zurück, falls keine Datei ausgewählt wurde
    }
}
