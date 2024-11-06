package org.example;

// Die Klasse Statistik verfolgt die Anzahl der Versuche und richtigen Antworten eines Nutzers.
public class Statistik {
    private int versuche;  // Speichert die Gesamtanzahl der Versuche
    private int richtigeAntworten;  // Speichert die Anzahl der richtigen Antworten

    // Konstruktor: Initialisiert die Anzahl der Versuche und richtigen Antworten mit 0.
    public Statistik() {
        this.versuche = 0;
        this.richtigeAntworten = 0;
    }

    // Methode zum Hinzufügen eines weiteren Versuchs.
    public void addVersuch() {
        this.versuche++;
    }

    // Methode zum Hinzufügen einer weiteren richtigen Antwort.
    public void addRichtigeAntwort() {
        this.richtigeAntworten++;
    }

    // Getter-Methode: Gibt die Gesamtanzahl der Versuche zurück.
    public int getVersuche() {
        return this.versuche;
    }

    // Getter-Methode: Gibt die Anzahl der richtigen Antworten zurück.
    public int getRichtigeAntworten() {
        return this.richtigeAntworten;
    }

    // Berechnet die Erfolgsquote in Prozent, basierend auf den richtigen Antworten und den Versuchen.
    public double quote() {
        return (double) this.richtigeAntworten / this.versuche * 100;
    }

    // Gibt die Anzahl der falschen Antworten zurück.
    public int falscheAntworten() {
        return this.versuche - this.richtigeAntworten;
    }

    // Setter-Methode: Setzt die Anzahl der richtigen Antworten auf einen bestimmten Wert.
    public void setRichtigeAntworten(int richtigeAntworten) {
        this.richtigeAntworten = richtigeAntworten;
    }

    // Setter-Methode: Setzt die Anzahl der Versuche auf einen bestimmten Wert.
    public void setVersuche(int versuche) {
        this.versuche = versuche;
    }
}
