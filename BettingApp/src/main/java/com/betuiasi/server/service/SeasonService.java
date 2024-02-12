package com.betuiasi.server.service;

import com.betuiasi.server.persistence.model.CompetitionModel;
import com.betuiasi.server.persistence.model.MatchModel;
import com.betuiasi.server.persistence.model.SeasonModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class SeasonService {
    private final MongoTemplate mongoTemplate;

    @Autowired
    public SeasonService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public void insertSeason(SeasonModel season) {
        mongoTemplate.save(season);
    }
    public boolean seasonExists(SeasonModel season) {
        var seasons = mongoTemplate.findAll(SeasonModel.class);
        var existingSeason = seasons.stream().filter(x -> Objects.equals(x.getId(), season.getId())).findFirst();
        return existingSeason.isPresent();
    }
    public void insertSeasonIfDoenstExist(SeasonModel season) {
        if (!seasonExists(season)) {
            insertSeason(season);
        }
    }
    public void clearAll(){
        mongoTemplate.dropCollection(SeasonModel.class);
    }

    public List<SeasonModel> findSeasons()
    {
        var seasons = mongoTemplate.findAll(SeasonModel.class);
        return seasons;
    }

    public Optional<SeasonModel> findSeasonById(Long id)
    {
        var matches = mongoTemplate.findAll(SeasonModel.class);
        return matches.stream().filter(x -> x.getId().equals(id)).findFirst();
    }
}
