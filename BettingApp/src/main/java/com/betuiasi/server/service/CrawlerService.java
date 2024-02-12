package com.betuiasi.server.service;

import org.antlr.v4.runtime.misc.Pair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class CrawlerService {

    public List<String> crawl_links(String url) {
        List<String> linksList = new ArrayList<>();

        try {

            Document document = Jsoup.connect(url).get();
            Elements links = document.select("a[href]");

            for (Element link : links) {
                String href = link.attr("href");
                linksList.add(href);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return linksList;
    }

    public String crawl_content(String url) {
        String textContent = "";
        try {
            Document document = Jsoup.connect(url).get();
            textContent = document.text();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return textContent;
    }

    public List<String> filterList(List<String> stringList, String mustContain) {
        List<String> filteredList = new ArrayList<>();
        Iterator<String> iterator = stringList.iterator();

        while (iterator.hasNext()) {
            String element = iterator.next();

            if (element.contains(mustContain) && !element.contains("http") && !element.contains("https")) {
                filteredList.add(element);
            }
        }

        return filteredList;
    }

    public String getActualContent(String content) {
        List<String> splitString = new ArrayList<>(Arrays.asList(content.split("/")));

        Iterator<String> iterator = splitString.iterator();
        String resultString = "";

        while (iterator.hasNext()) {
            String element = iterator.next();
            if (element.contains("Citește și")) {
                resultString = element.substring(element.lastIndexOf('/') + 1, element.indexOf("Citește și")).trim();
            }
        }

        return resultString;
    }

    public List<String> removeDuplicates(List<String> list) {
        Set<String> set = new HashSet<>(list);
        return new ArrayList<>(set);
    }

    public List<Pair<String, String>> getHeadlines(String url) {
        List<String> links;
        links = removeDuplicates(filterList(crawl_links(url), "international/"));

        List<Pair<String, String>> headlines = new ArrayList<>();
        for (var link : links) {
            url = "https://www.gsp.ro/" + link;
            String content = crawl_content(url);
            String actualContent = getActualContent(content);
            if (!actualContent.trim().isEmpty()) {
                Pair<String, String> headlinePair = new Pair<>(actualContent, url);
                headlines.add(headlinePair);
            }
        }
        return headlines;
    }
}
