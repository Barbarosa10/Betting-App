package com.betuiasi.server.controller;

import com.betuiasi.server.persistence.model.CompetitionModel;
import com.betuiasi.server.persistence.model.ScoreModel;
import com.betuiasi.server.persistence.model.SeasonModel;
import com.betuiasi.server.service.ScoreService;
import com.betuiasi.server.service.SeasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/seasons")
public class SeasonController {
    private final SeasonService seasonService;

    @Autowired
    public SeasonController(SeasonService seasonService) {
        this.seasonService = seasonService;
    }

    @GetMapping("")
    public ResponseEntity<?> findSeasons(){
        List<SeasonModel> foundSeasons = seasonService.findSeasons();
        return foundSeasons.isEmpty() ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                new ResponseEntity<>(foundSeasons, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SeasonModel> findSeasonById(@PathVariable Long id){
        Optional<SeasonModel> foundSeason = seasonService.findSeasonById(id);

        return foundSeason.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
