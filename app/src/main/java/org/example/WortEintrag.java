package org.example;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Die Klasse WortEintrag repräsentiert einen Eintrag mit einem Wort und einer URL.
public class WortEintrag {
    private String word;  // Speichert das Wort
    private String url;   // Speichert die URL

    // Konstruktor, der ein Wort und eine URL entgegennimmt und überprüft, ob die URL gültig ist.
    public WortEintrag(String word, String url)
            throws IllegalArgumentException
    {
        // Überprüfen, ob das Wort leer ist oder die URL ungültig ist.
        if(word.equals("") || !checkURL(url))
            throw new IllegalArgumentException();

        this.word = word;
        this.url = url;
    }

    // Statische Methode zur Überprüfung, ob die übergebene URL gültig ist.
    public static boolean checkURL(String url) {
        // Überprüfen, ob die URL mit "http://" oder "https://" beginnt.
        if(url.startsWith("http://") || url.startsWith("https://"))
        {
            // Muster zum Überprüfen des Formats einer URL.
            Pattern pattern = Pattern.compile("^((http|https)://)?[a-z0-9]+([\\-\\.]{1}[a-z0-9]+)*\\.[a-z]{2,5}(:[0-9]{1,5})?(\\/.*)?$");
            Matcher matcher = pattern.matcher(url);
            // Wenn die URL dem Muster entspricht, ist sie gültig.
            if (matcher.matches()) {
                return true;
            }
        }
        // Wenn die URL nicht dem Muster entspricht oder nicht mit "http://" oder "https://" beginnt, ist sie ungültig.
        return false;
    }

    // Gibt das gespeicherte Wort zurück.
    public String getWord() {
        return this.word;
    }

    // Gibt die gespeicherte URL zurück.
    public String getUrl() {
        return url;
    }
}
