package com.betuiasi.server.service;

import com.betuiasi.server.persistence.model.PlayerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class PlayerService {
    private final MongoTemplate mongoTemplate;

    @Autowired
    public PlayerService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public void insertPlayer(PlayerModel player) {
        mongoTemplate.save(player);
    }
    public void clearAll(){
        mongoTemplate.dropCollection(PlayerModel.class);
    }
    public boolean playerExists(PlayerModel player) {
        var players = mongoTemplate.findAll(PlayerModel.class);
        var existingPlayer = players.stream().filter(x -> Objects.equals(x.getId(), player.getId())).findFirst();
        return existingPlayer.isPresent();
    }
    public void insertPlayerIfDoenstExist(PlayerModel player) {
        if (!playerExists(player)) {
            insertPlayer(player);
        }
    }
}

