package ua.com.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Main {
    public Main() {
        Document doc = null;
        try {
            doc = Jsoup.connect("https://www.google.com.ua/search?q=fucker&tbm=isch").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements newsHeadlines = doc.select("#rg_s img:first");
        System.out.println(newsHeadlines);
    }
}
