package org.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Die Klasse Rechtschreibtrainer stellt einen Trainer zur Überprüfung der Rechtschreibung zur Verfügung.
 * Es verwaltet eine Liste von WortEinträgen und behält Statistiken über die Benutzereingaben.
 * Und es überprüft, ob das eingegebene Wort mit dem aktuellen Wort übereinstimmt.
 *
 * @version 1.0
 * @autor davidbednarik
 */



// Die Klasse Rechtschreibtrainer stellt einen Trainer zur Überprüfung der Rechtschreibung zur Verfügung.
// Sie verwaltet eine Liste von WortEinträgen und behält Statistiken über die Benutzereingaben.
public class Rechtschreibtrainer {
    private List<WortEintrag> wortliste;  // Liste von WortEinträgen, die der Trainer verwendet
    private Statistik statistik;  // Statistik-Objekt zur Verfolgung der richtigen und falschen Antworten
    private int aktuellerEintrag;  // Index des aktuell zu überprüfenden Wortes in der wortliste
    private int letzterEintrag = -1;  // Index des zuletzt überprüften Wortes in der wortliste

    // Konstruktor, der eine Liste von WortEinträgen entgegennimmt und eine neue Statistik erstellt.
    public Rechtschreibtrainer(List<WortEintrag> wortliste) {
        this.wortliste = new ArrayList<>();
        for (WortEintrag wortEintrag : wortliste) {
            if (wortEintrag.getDefect() == false) {
                this.wortliste.add(wortEintrag);

            }
        }
        this.statistik = new Statistik();
    }

    // Konstruktor, der eine Liste von WortEinträgen und eine bestehende Statistik verwendet.
    public Rechtschreibtrainer(List<WortEintrag> wortliste, Statistik statistik) {
        this.wortliste = wortliste;
        this.statistik = statistik;
    }

    // Wählt zufällig einen Eintrag aus der Wortliste aus.
    public void zufaelligerEintrag() {
        if (wortliste.size() <= 1) {
            this.aktuellerEintrag = 0;
            return;
        }

        int neuerEintrag;
        do {
            neuerEintrag = (int) (Math.random() * wortliste.size());
        } while (neuerEintrag == letzterEintrag);

        letzterEintrag = neuerEintrag;
        this.aktuellerEintrag = neuerEintrag;
    }

    // Gibt den aktuell ausgewählten WortEintrag zurück. Falls kein Eintrag ausgewählt wurde, wird zufällig einer ausgewählt.
    public WortEintrag getAktuellerEintrag() {
        if (this.aktuellerEintrag == -1) {
            this.zufaelligerEintrag();
        }
        return this.wortliste.get(this.aktuellerEintrag);
    }

    // Setzt den aktuellen Eintrag auf den angegebenen Index.
    public void setAktuellerEintrag(int index) {
        this.aktuellerEintrag = index;
    }

    // Überprüft, ob das eingegebene Wort exakt mit dem aktuellen Wort übereinstimmt.
    // Aktualisiert die Statistik entsprechend.
    public boolean check(String wort) {
        if (wort.equals(this.wortliste.get(this.aktuellerEintrag).getWord())) {
            this.statistik.addRichtigeAntwort();
            this.statistik.addVersuch();
            return true;
        }
        this.statistik.addVersuch();
        return false;
    }

    // Überprüft, ob das eingegebene Wort unabhängig von Groß- und Kleinschreibung mit dem aktuellen Wort übereinstimmt.
    // Aktualisiert die Statistik entsprechend.
    public boolean checkIgnoreCase(String wort) {
        if (wort.equalsIgnoreCase(this.wortliste.get(this.aktuellerEintrag).getWord())) {
            this.statistik.addRichtigeAntwort();
            this.statistik.addVersuch();
            return true;
        }
        this.statistik.addVersuch();
        return false;
    }

    // Gibt die Statistik zurück, die die Versuche und richtigen Antworten enthält.
    public Statistik getStatistik() {
        return this.statistik;
    }

    // Gibt die gesamte Wortliste zurück.
    public List<WortEintrag> getWortliste() {
        return wortliste;
    }
}
