package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

// Die Klasse Controller verwaltet die Benutzerinteraktionen und steuert den Ablauf der Anwendung.
public class Controller implements ActionListener {
    private Rechtschreibtrainer trainer;  // Instanz des Rechtschreibtrainers zur Verwaltung von Wörtern und Statistiken
    private GUIFenster playScreen;  // Fenster für den Hauptspielbildschirm
    private StartScreen startScreen;  // Fenster für den Startbildschirm

    // Getter-Methode: Gibt den aktuellen Rechtschreibtrainer zurück
    public Rechtschreibtrainer getTrainer() {
        return this.trainer;
    }

    // Hauptmethode: Einstiegspunkt der Anwendung
    public static void main(String[] args) throws MalformedURLException {
        Controller con = new Controller();
        con.setup();  // Initialisiert den Startbildschirm und Trainer
    }

    // Methode zur Einrichtung des Startbildschirms und des Trainers
    public void setup() {
        // Erstelle Beispiel-WortEinträge mit Wörtern und dazugehörigen Bild-URLs
        WortEintrag we = new WortEintrag("Tiger", "https://files.worldwildlife.org/wwfcmsprod/images/Tiger_resting_Bandhavgarh_National_Park_India/hero_small/6aofsvaglm_Medium_WW226365.jpg");
        WortEintrag we2 = new WortEintrag("Elefant", "https://cdn.britannica.com/02/152302-050-1A984FCB/African-savanna-elephant.jpg");
        WortEintrag we3 = new WortEintrag("Auto", "https://cdn.motor1.com/images/mgl/EKJ2B/s3/auto-neuheiten-2021-2022.jpg");
        WortEintrag we4 = new WortEintrag("Banane", "https://upload.wikimedia.org/wikipedia/commons/thumb/8/8a/Banana-Single.jpg/545px-Banana-Single.jpg");
        WortEintrag we5 = new WortEintrag("Google", "https://www.google.de/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png");

        List<WortEintrag> wl = List.of(we, we2, we3, we4, we5);

        // Initialisiere den Trainer mit der Wortliste und wähle zufällig ein Wort aus
        this.trainer = new Rechtschreibtrainer(wl);
        trainer.zufaelligerEintrag();

        // Erstelle den Startbildschirm
        startScreen = new StartScreen(this);
    }

    // Überschriebene Methode, die auf Benutzeraktionen reagiert
    @Override
    public void actionPerformed(ActionEvent e) {
        // Startet das Spiel vom Startbildschirm aus
        if (e.getSource().equals(startScreen.start)) {
            startScreen.dispose();  // Schließe den Startbildschirm
            try {
                playScreen = new GUIFenster(this);  // Öffne das Hauptspielfenster
                playScreen.layout.statistikAkt(this.trainer.getStatistik().getRichtigeAntworten(), this.trainer.getStatistik().getVersuche());
            } catch (MalformedURLException ex) {
                System.err.println("Probleme mit der URL");
            }
        }
        // Lädt ein gespeichertes Spiel
        else if (e.getSource().equals(startScreen.load)) {
            try {
                this.trainer = new WortSL().load(startScreen.fileAussuchen());
                startScreen.dispose();
                playScreen = new GUIFenster(this);
                playScreen.layout.statistikAkt(this.trainer.getStatistik().getRichtigeAntworten(), this.trainer.getStatistik().getVersuche());
            } catch (IOException | NullPointerException err) {
                JOptionPane.showMessageDialog(null, "File not valid");
            }
        }
        // Setzt die Statistik im Spiel zurück
        else if (e.getSource().equals(playScreen.layout.reset)) {
            this.trainer.getStatistik().setRichtigeAntworten(0);
            this.trainer.getStatistik().setVersuche(0);
            this.trainer.zufaelligerEintrag();
            try {
                playScreen.layout.statistikAkt(this.trainer.getStatistik().getRichtigeAntworten(), this.trainer.getStatistik().getVersuche());
            } catch (MalformedURLException ex) {
                throw new RuntimeException(ex);
            }
        }
        // Überprüft die Benutzereingabe und aktualisiert den Trainer und das Layout
        else if (e.getSource().equals(playScreen.layout.addWort)) {
            if (this.trainer.check(playScreen.layout.getText())) {
                try {
                    this.trainer.zufaelligerEintrag();
                    playScreen.layout.statistikAkt(this.trainer.getStatistik().getRichtigeAntworten(), this.trainer.getStatistik().getVersuche());
                } catch (MalformedURLException ex) {
                    throw new RuntimeException(ex);
                }
            } else {
                try {
                    this.trainer.zufaelligerEintrag();
                    playScreen.layout.statistikAkt(this.trainer.getStatistik().getRichtigeAntworten(), this.trainer.getStatistik().getVersuche());
                } catch (MalformedURLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
        // Beendet das Spiel und kehrt zum Startbildschirm zurück
        else if (e.getSource().equals(playScreen.layout.zurueck)) {
            playScreen.dispose();
            startScreen = new StartScreen(this);
        }
        // Speichert den Fortschritt des Spiels
        if (e.getSource().equals(playScreen.layout.save)) {
            playScreen.layout.speichern();
        }
    }
}
