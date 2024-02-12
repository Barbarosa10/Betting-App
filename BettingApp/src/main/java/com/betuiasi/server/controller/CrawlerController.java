package com.betuiasi.server.controller;

import org.antlr.v4.runtime.misc.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.betuiasi.server.service.CrawlerService;

import java.util.List;

@RequestMapping("/api/crawl")
@RestController
public class CrawlerController {

    @Autowired
    private CrawlerService crawlerService;

    @GetMapping("/{country}")
    public ResponseEntity<List<Pair<String, String>>> crawl(@PathVariable String country) {
        String url = "https://www.gsp.ro/fotbal/";

        return switch (country) {
            case "england" ->
                    new ResponseEntity<>(crawlerService.getHeadlines(url + "premier-league"), HttpStatus.OK);
            case "italy" ->
                    new ResponseEntity<>(crawlerService.getHeadlines(url + "serie-a"), HttpStatus.OK);
            case "germany" ->
                    new ResponseEntity<>(crawlerService.getHeadlines(url + "bundesliga"), HttpStatus.OK);
            case "spain" ->
                    new ResponseEntity<>(crawlerService.getHeadlines(url + "la-liga"), HttpStatus.OK);
            default -> ResponseEntity.notFound().build();
        };
    }

}

