package com.betuiasi.server.service;

import com.betuiasi.server.persistence.model.AreaModel;
import com.betuiasi.server.persistence.model.CompetitionModel;
import com.betuiasi.server.persistence.model.MatchModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import java.util.List;

import java.util.Objects;
import java.util.Optional;

@Service
public class CompetitionService {
    private final MongoTemplate mongoTemplate;

    @Autowired
    public CompetitionService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public void clearAll(){
        mongoTemplate.dropCollection(CompetitionModel.class);
    }
    public void insertCompetition(CompetitionModel competition) {
        mongoTemplate.save(competition);
    }
    public boolean competitionExists(CompetitionModel competition) {
        var competitions = mongoTemplate.findAll(CompetitionModel.class);
        var existingCompetition = competitions.stream().filter(x -> Objects.equals(x.getId(), competition.getId())).findFirst();
        return existingCompetition.isPresent();
    }
    public void insertCompetitionIfDoenstExist(CompetitionModel competition) {
        if (!competitionExists(competition)) {
            insertCompetition(competition);
        }
    }
    public List<CompetitionModel> findCompetitions() {
        var competitions = mongoTemplate.findAll(CompetitionModel.class);
        return competitions;
    }
    public Optional<CompetitionModel> findCompetitionByName(String nume) {
        var competitions = mongoTemplate.findAll(CompetitionModel.class);
        return competitions.stream().filter(x -> x.getName().toLowerCase().contains(nume.toLowerCase())).findFirst();
    }
    public Optional<CompetitionModel> findCompetitionByCode(String code) {
        var competitions = mongoTemplate.findAll(CompetitionModel.class);
        return competitions.stream().filter(x -> x.getCode().toLowerCase().contains(code.toLowerCase())).findFirst();
    }
}
