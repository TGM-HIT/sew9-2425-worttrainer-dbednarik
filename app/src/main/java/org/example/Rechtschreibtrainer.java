package org.example;

import java.util.List;

public class Rechtschreibtrainer {
    private List<WortEintrag> wortliste;
    private Statistik statistik;
    private int aktuellerEintrag;

    public Rechtschreibtrainer(List<WortEintrag> wortliste) {
        this.wortliste = wortliste;
        this.statistik = new Statistik();

    }

    public void zufaelligerEintrag() {
        this.aktuellerEintrag = (int) (Math.random() * wortliste.size());
    }

    public WortEintrag getAktuellerEintrag() {
        if (this.aktuellerEintrag == -1) {
            this.zufaelligerEintrag();
        }
        return this.wortliste.get(this.aktuellerEintrag);
    }
    public void setAktuellerEintrag(int index) {
        this.aktuellerEintrag = index;
    }
    public boolean check(String wort) {
        if (wort.equals(this.wortliste.get(this.aktuellerEintrag).getWord())) {
            this.statistik.addRichtigeAntwort();
            this.statistik.addVersuch();
            return true;
        }
        this.statistik.addVersuch();
        return false;
    }
    public boolean checkIgnoreCase(String wort) {
        if (wort.equalsIgnoreCase(this.wortliste.get(this.aktuellerEintrag).getWord())) {
            this.statistik.addRichtigeAntwort();
            this.statistik.addVersuch();
            return true;
        }
        this.statistik.addVersuch();
        return false;
    }

    public Statistik getStatistik() {
        return this.statistik;
    }
}
