package com.betuiasi.server.controller;

import org.antlr.v4.runtime.misc.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/tv")
public class TVBroadcastController {

    @GetMapping("/digi1")
    public ResponseEntity<String> digi1() {
        String url = "https://manutv.org/tvlive/index.php?url=dg-sport-1";
        return new ResponseEntity<>(url, HttpStatus.OK);
    }
    @GetMapping("/digi2")
    public ResponseEntity<String> digi2() {
        String url = "https://manutv.org/manutv/index.php?url=digisport-2";
        return new ResponseEntity<>(url, HttpStatus.OK);
    }
    @GetMapping("/digi3")
    public ResponseEntity<String> digi3() {
        String url = "https://manutv.org/tvlive/index.php?url=digisport-3";
        return new ResponseEntity<>(url, HttpStatus.OK);
    }
    @GetMapping("/digi4")
    public ResponseEntity<String> digi4() {
        String url = "https://manutv.org/tvlive/index.php?url=digisport-4";
        return new ResponseEntity<>(url, HttpStatus.OK);
    }
}
