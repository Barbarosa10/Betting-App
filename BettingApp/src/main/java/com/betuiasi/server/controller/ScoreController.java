package com.betuiasi.server.controller;

import com.betuiasi.server.persistence.model.CompetitionModel;
import com.betuiasi.server.persistence.model.MatchModel;
import com.betuiasi.server.persistence.model.ScoreModel;
import com.betuiasi.server.service.ScoreService;
import com.betuiasi.server.service.SeasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/scores")
public class ScoreController {
    private final ScoreService scoreService;

    @Autowired
    public ScoreController(ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    @GetMapping("")
    public ResponseEntity<?> findScores(){
        List<ScoreModel> foundScores = scoreService.findScores();
        return foundScores.isEmpty() ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                new ResponseEntity<>(foundScores, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScoreModel> findScoreById(@PathVariable String id){
        Optional<ScoreModel> foundScore = scoreService.findScoreById(id);

        return foundScore.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
