package com.betuiasi.server.service;


import com.betuiasi.server.persistence.model.CompetitionModel;
import com.betuiasi.server.persistence.model.TeamModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class TeamService {
    private final MongoTemplate mongoTemplate;

    @Autowired
    public TeamService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public void insertTeam(TeamModel team) {
        mongoTemplate.save(team);
    }
    public boolean teamExists(TeamModel team) {
        var teams = mongoTemplate.findAll(TeamModel.class);
        var existingTeam = teams.stream().filter(x -> Objects.equals(x.getId(), team.getId())).findFirst();
        return existingTeam.isPresent();
    }
    public void insertTeamIfDoenstExist(TeamModel team) {
        if (!teamExists(team)) {
            insertTeam(team);
        }
    }
    public void clearAll(){
        mongoTemplate.dropCollection(TeamModel.class);
    }
}
