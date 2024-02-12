package com.betuiasi.server.controller;

import com.betuiasi.server.persistence.model.MatchModel;
import com.betuiasi.server.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/matches")
public class MatchController {
    private final MatchService matchService;

    @Autowired
    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping("")
    public ResponseEntity<?> findMatches(){
        List<MatchModel> foundMatches = matchService.findMatches();
        foundMatches.forEach(m -> m.setSportType(matchService.generateSportType(m.getId())));
        return foundMatches.isEmpty() ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                new ResponseEntity<>(foundMatches, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MatchModel> findMatchById(@PathVariable Long id){
        Optional<MatchModel> foundMatches = matchService.findMatchById(id);

        return foundMatches.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
