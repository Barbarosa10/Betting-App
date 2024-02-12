package com.betuiasi.server.controller;

import com.betuiasi.server.persistence.model.CompetitionModel;
import com.betuiasi.server.service.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/competitions")
public class CompetitionController {

    private final CompetitionService competitionService;

    @Autowired
    public CompetitionController(CompetitionService competitionService) {
        this.competitionService = competitionService;
    }

    @GetMapping("")
    public ResponseEntity<?> findCompetition(@RequestParam Optional<String> nume, @RequestParam Optional<String> code){
        if(nume.isPresent()){
            Optional<CompetitionModel> foundCompetition = competitionService.findCompetitionByName(nume.get());
            return foundCompetition.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        }else if(code.isPresent()){
            Optional<CompetitionModel> foundCompetition = competitionService.findCompetitionByCode(code.get());
            return foundCompetition.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        }

        List<CompetitionModel> foundCompetitions = competitionService.findCompetitions();
        return foundCompetitions.isEmpty() ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                new ResponseEntity<>(foundCompetitions, HttpStatus.OK);
    }
}
