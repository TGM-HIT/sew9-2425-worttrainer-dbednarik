package org.example;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class WortSL {
    private final String DEFAULT_SAVE = "../Worttrainer.json";

    public void save(Rechtschreibtrainer tr) throws IOException {
        save(tr, DEFAULT_SAVE);
    }
    public Rechtschreibtrainer load() throws IOException {
        return load(this.DEFAULT_SAVE);
    }


    public void save(Rechtschreibtrainer tr, String path)
        throws IOException
    {
        JSONObject stats = new JSONObject();
        stats.put("versuche", tr.getStatistik().getVersuche());
        stats.put("richtigeAntworten", tr.getStatistik().getRichtigeAntworten());


        JSONObject trainer = new JSONObject();
        trainer.put("wortliste", tr.getWortliste());
        trainer.put("statistik", stats);

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(path)))
        {
            writer.write(trainer.toString(4));
        } catch(IOException e)
        {
            System.err.println("Fehler beim speichern: " + e.toString());
        }

    }

    public Rechtschreibtrainer load(String path)
        throws IOException
    {
        try(BufferedReader reader = new BufferedReader(new FileReader(path)))
        {
            StringBuilder json = new StringBuilder();
            String line;
            while((line = reader.readLine()) != null)
            {
                json.append(line);
            }
            JSONObject trainer = new JSONObject(json.toString());

            Statistik statistik = new Statistik();
            statistik.setVersuche(trainer.getJSONObject("statistik").getInt("versuche"));
            statistik.setRichtigeAntworten(trainer.getJSONObject("statistik").getInt("richtigeAntworten"));

            JSONArray wortliste = trainer.getJSONArray("wortliste");
            List<WortEintrag> wortEintragList = new ArrayList<>();
            for(int i = 0; i < wortliste.length(); i++)
            {
                JSONObject wort = wortliste.getJSONObject(i);
                wortEintragList.add(new WortEintrag(wort.getString("word"), wort.getString("url")));
            }

            return new Rechtschreibtrainer(wortEintragList, statistik);

        } catch(IOException e)
        {
            System.err.println("Fehler beim Laden: " + e.toString());
        }
        return null;
    }
}
