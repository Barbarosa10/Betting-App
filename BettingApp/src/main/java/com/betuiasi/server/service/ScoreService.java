package com.betuiasi.server.service;

import com.betuiasi.server.Component.ScheduledTask;
import com.betuiasi.server.persistence.model.ScoreModel;
import com.betuiasi.server.persistence.model.SeasonModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ScoreService {
    private final MongoTemplate mongoTemplate;
    private static final Logger log = LoggerFactory.getLogger(ScoreService.class);

    @Autowired
    public ScoreService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public void insertScore(ScoreModel score) {
        mongoTemplate.save(score);
    }

    public void clearAll(){
        mongoTemplate.dropCollection(ScoreModel.class);
    }

    public List<ScoreModel> findScores()
    {
        var scores = mongoTemplate.findAll(ScoreModel.class);
        return scores;
    }

    public Optional<ScoreModel> findScoreById(String id)
    {
        var scores = mongoTemplate.findAll(ScoreModel.class);
        log.info("id1: " + id);
        return scores.stream().filter(x -> {log.info("id2: " + x.getId()); return x.getId().equals(id);}).findFirst();
    }
}
