package org.example;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Die Klasse WortSL bietet Methoden zum Speichern und Laden von Rechtschreibtrainern in eine JSON-Datei.
 * @version 1.0
 * @autor davidbednarik
 */




// Die Klasse WortSL bietet Methoden zum Speichern und Laden von Rechtschreibtrainern in eine JSON-Datei.
public class WortSL {
    private final String DEFAULT_SAVE = "../Worttrainer.json"; // Standardpfad für die Speicherdatei

    // Speichert den übergebenen Rechtschreibtrainer unter dem Standardpfad.
    public void save(Rechtschreibtrainer tr) throws IOException {
        save(tr, DEFAULT_SAVE);
    }

    // Lädt den Rechtschreibtrainer vom Standardpfad.
    public Rechtschreibtrainer load() throws IOException {
        return load(this.DEFAULT_SAVE);
    }

    // Speichert den Rechtschreibtrainer an einem angegebenen Pfad.
    public void save(Rechtschreibtrainer tr, String path) throws IOException {
        // Erstelle ein JSONObject für die Statistik des Trainers.
        JSONObject stats = new JSONObject();
        stats.put("versuche", tr.getStatistik().getVersuche());
        stats.put("richtigeAntworten", tr.getStatistik().getRichtigeAntworten());

        // Erstelle das JSONObject für den Trainer und füge die Wortliste und die Statistik hinzu.
        JSONObject trainer = new JSONObject();
        trainer.put("wortliste", tr.getWortliste());
        trainer.put("statistik", stats);

        // Schreibe das JSONObject in die Datei.
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            writer.write(trainer.toString(4)); // Formatierte Ausgabe mit Einrückungen für bessere Lesbarkeit
        } catch (IOException e) {
            System.err.println("Fehler beim Speichern: " + e.toString());
        }
    }

    // Lädt den Rechtschreibtrainer von einem angegebenen Pfad.
    public Rechtschreibtrainer load(String path) throws IOException {
        // Lese die Datei zeilenweise und baue den JSON-String zusammen.
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            StringBuilder json = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                json.append(line);
            }
            JSONObject trainer = new JSONObject(json.toString());

            // Erstelle das Statistik-Objekt aus den gespeicherten Werten.
            Statistik statistik = new Statistik();
            statistik.setVersuche(trainer.getJSONObject("statistik").getInt("versuche"));
            statistik.setRichtigeAntworten(trainer.getJSONObject("statistik").getInt("richtigeAntworten"));

            // Erstelle die Wortliste aus dem JSON-Array.
            JSONArray wortliste = trainer.getJSONArray("wortliste");
            List<WortEintrag> wortEintragList = new ArrayList<>();
            for (int i = 0; i < wortliste.length(); i++) {
                JSONObject wort = wortliste.getJSONObject(i);
                wortEintragList.add(new WortEintrag(wort.getString("word"), wort.getString("url")));
            }

            // Erstelle und gebe den Rechtschreibtrainer zurück.
            return new Rechtschreibtrainer(wortEintragList, statistik);

        } catch (IOException e) {
            System.err.println("Fehler beim Laden: " + e.toString());
        }
        return null; // Gibt null zurück, wenn es ein Problem beim Laden gab.
    }
}
