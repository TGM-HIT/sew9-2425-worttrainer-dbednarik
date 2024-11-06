package org.example;

import org.example.WortSL;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Die Klasse GUIKomponenten erstellt die grafische Benutzeroberfläche für den Rechtschreibtrainer.
 *
 * @author davidbednarik
 * @version 1.0
 */
public class GUIKomponenten extends JPanel {
    private Controller con;  // Referenz zum Controller
    private JTextField textField;  // Eingabefeld für den Benutzer
    private ImageIcon icon;  // Icon zur Darstellung eines Bildes
    private Image image;  // Bild, das skaliert wird
    private JLabel lImage;  // Label zur Anzeige des Bildes

    private JPanel south;  // Panel für die untere Region des Layouts
    private JPanel southWest;  // Panel für die linke untere Region
    private JPanel southCenter;  // Panel für die mittlere untere Region
    private JPanel southEast;  // Panel für die rechte untere Region

    // Labels zur Anzeige von Statistiken
    private JLabel labelRichtigeWoerter;
    private JLabel labelVersuche;
    private JLabel labelQuote;
    private JLabel counterRichtigeWoerter;
    private JLabel counterVersuche;
    private JLabel average;

    // Buttons zur Benutzerinteraktion
    public JButton reset;
    public JButton addWort;
    public JButton zurueck;
    public JButton save;

    // Konstruktor: Initialisiert die Benutzeroberfläche
    public GUIKomponenten(Controller con) throws MalformedURLException {
        this.con = con;

        // Verwende ein BorderLayout als Basislayout
        BorderLayout basis = new BorderLayout();
        this.setLayout(basis);

        // Erstelle ein Eingabefeld
        this.textField = new JTextField();
        this.textField.setOpaque(true);
        this.textField.setPreferredSize(new Dimension(450, 20));

        // Setze Layout für den unteren Bereich
        GridLayout grid = new GridLayout(1, 3);
        this.south = new JPanel();
        this.south.setLayout(grid);

        // Panel für die Statistiken (linker unterer Bereich)
        this.southWest = new JPanel(new GridLayout(3, 1));
        this.labelRichtigeWoerter = new JLabel("Richtige Wörter:");
        this.labelVersuche = new JLabel("Versuche:");
        this.labelQuote = new JLabel("Quote:");
        this.southWest.add(labelRichtigeWoerter);
        this.southWest.add(labelVersuche);
        this.southWest.add(labelQuote);

        // Panel für die Zähler (mittlerer unterer Bereich)
        this.southCenter = new JPanel(new GridLayout(3, 1));
        this.counterRichtigeWoerter = new JLabel("0");
        this.counterVersuche = new JLabel("0");
        this.average = new JLabel("0%");
        this.southCenter.add(counterRichtigeWoerter);
        this.southCenter.add(counterVersuche);
        this.southCenter.add(average);

        // Panel für die Buttons (rechter unterer Bereich)
        this.southEast = new JPanel(new GridLayout(4, 1));
        this.reset = new JButton("Reset");
        this.reset.addActionListener(this.con);
        this.addWort = new JButton("Wort hinzufügen");
        this.addWort.addActionListener(this.con);
        this.zurueck = new JButton("Spiel beenden");
        this.zurueck.addActionListener(this.con);
        this.save = new JButton("Save");
        this.save.addActionListener(this.con);
        this.southEast.add(reset);
        this.southEast.add(addWort);
        this.southEast.add(zurueck);
        this.southEast.add(save);

        // Füge die unteren Panels zum Gesamtpanel hinzu
        this.south.add(southWest);
        this.south.add(southCenter);
        this.south.add(southEast);

        // Lade das aktuelle Bild für das Wort
        try {
            this.icon = new ImageIcon(new URL(con.getTrainer().getAktuellerEintrag().getUrl()));
        } catch (MalformedURLException err) {
            err.printStackTrace();
            return;
        }
        this.image = icon.getImage(); // Umwandeln in ein Image-Objekt
        this.image = image.getScaledInstance(200, 200, Image.SCALE_SMOOTH); // Skalieren auf gewünschte Größe
        this.lImage = new JLabel(new ImageIcon(image));

        // Füge die Komponenten dem Layout hinzu
        this.add(lImage, basis.CENTER);
        this.add(textField, basis.NORTH);
        this.add(south, basis.SOUTH);
    }

    // Gibt den eingegebenen Text zurück und leert das Textfeld.
    public String getText() {
        String r = this.textField.getText();
        this.textField.setText("");
        return r;
    }

    // Aktualisiert die Statistiken in der Benutzeroberfläche.
    public void statistikAkt(int correct, int versuche) throws MalformedURLException {
        // Lade das Bild des aktuellen Wortes neu.
        try {
            this.icon = new ImageIcon(new URL(con.getTrainer().getAktuellerEintrag().getUrl()));
        } catch (MalformedURLException err) {
            err.printStackTrace();
            return;
        }
        this.image = icon.getImage(); // Umwandeln in ein Image-Objekt
        this.image = image.getScaledInstance(200, 200, Image.SCALE_SMOOTH); // Skalieren auf gewünschte Größe
        this.lImage.setIcon(new ImageIcon(image));

        // Aktualisiere die Statistiken.
        this.counterVersuche.setText(String.valueOf(versuche));
        this.counterRichtigeWoerter.setText(String.valueOf(correct));
        this.average.setText(String.valueOf(con.getTrainer().getStatistik().quote()) + "%");
    }

    // Speichert den Trainer.
    public void speichern() {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showSaveDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                new WortSL().save(con.getTrainer(), selectedFile.getAbsolutePath());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Fehler beim Speichern");
                e.printStackTrace();
            }
        } else {
            // Wenn der Benutzer keinen spezifischen Speicherort auswählt, wird die Standarddatei verwendet.
            try {
                new WortSL().save(con.getTrainer());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Fehler beim Speichern");
                e.printStackTrace();
            }
        }
    }
}
