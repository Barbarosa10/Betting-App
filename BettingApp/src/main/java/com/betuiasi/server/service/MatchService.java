package com.betuiasi.server.service;

import com.betuiasi.server.persistence.model.AreaModel;
import com.betuiasi.server.persistence.model.CompetitionModel;
import com.betuiasi.server.persistence.model.MatchModel;
import com.betuiasi.server.persistence.model.MatchesModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class MatchService {
    private final MongoTemplate mongoTemplate;

    @Autowired
    public MatchService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public void insertMatch(MatchModel match) {
        mongoTemplate.save(match);
    }
    public void clearAll(){
        mongoTemplate.dropCollection(MatchModel.class);
    }
    public boolean matchExists(MatchModel match) {
        var matches = mongoTemplate.findAll(MatchModel.class);
        var existingMatch = matches.stream().filter(x -> Objects.equals(x.getId(), match.getId())).findFirst();
        return existingMatch.isPresent();
    }
    public void insertMatchIfNotExist(MatchModel match) {
        if (!matchExists(match)) {
            insertMatch(match);
        }
    }
    public List<MatchModel> findMatches()
    {
        var matches = mongoTemplate.findAll(MatchModel.class);
        return matches;
    }
    public Optional<MatchModel> findMatchById(Long id)
    {
        var matches = mongoTemplate.findAll(MatchModel.class);
        return matches.stream().filter(x -> x.getId().equals(id)).findFirst();
    }
    public String generateSportType(Long id) {
        return switch ((int) (id % 5)) {
            case 1 -> "Basketball";
            case 2 -> "Volleyball";
            case 3 -> "Tennis";
            case 4 -> "Rugby";
            default -> "Football";
        };
    }
}

