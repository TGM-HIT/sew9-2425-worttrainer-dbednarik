package org.example;

public class Statistik {
    private int versuche;
    private int richtigeAntworten;

    public Statistik()
    {
        this.versuche = 0;
        this.richtigeAntworten = 0;
    }

    public void addVersuch()
    {
        this.versuche++;
    }
    public void addRichtigeAntwort()
    {
        this.richtigeAntworten++;
    }
    public int getVersuche()
    {
        return this.versuche;
    }
    public int getRichtigeAntworten()
    {
        return this.richtigeAntworten;
    }
    public double quote()
    {
        return (double) this.richtigeAntworten / this.versuche * 100;
    }
    public int falscheAntworten()
    {
        return this.versuche - this.richtigeAntworten;
    }


    public void setRichtigeAntworten(int richtigeAntworten) {
        this.richtigeAntworten = richtigeAntworten;
    }

    public void setVersuche(int versuche) {
        this.versuche = versuche;
    }
}
