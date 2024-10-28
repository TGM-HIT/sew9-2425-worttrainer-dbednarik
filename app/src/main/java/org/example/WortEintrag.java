package org.example;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WortEintrag {
    private String word;
    private String url;

    public WortEintrag(String word, String url)
        throws IllegalArgumentException
    {
        if(word.equals("") || !checkURL(url))
            throw new IllegalArgumentException();
    }

    public static boolean checkURL(String url) {
        if(url.startsWith("http://") || url.startsWith("https://"))
        {
            Pattern pattern = Pattern.compile("^((http|https)://)?[a-z0-9]+([\\-\\.]{1}[a-z0-9]+)*\\.[a-z]{2,5}(:[0-9]{1,5})?(\\/.*)?$");
            Matcher matcher = pattern.matcher(url);
            if (matcher.matches()) {
                return true;
            }
        }
        return false;
    }

    public String getWord() {
        return this.word;
    }

    public String getUrl() {
        return url;
    }
}
