package com.betuiasi.server.service;

import com.betuiasi.server.persistence.model.AreaModel;
import com.betuiasi.server.persistence.model.CompetitionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AreaService {
    private final MongoTemplate mongoTemplate;

    @Autowired
    public AreaService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public void clearAll(){
        mongoTemplate.dropCollection(AreaModel.class);
    }
    public void insertArea(AreaModel area) {
        mongoTemplate.save(area);
    }
    public boolean areaExists(AreaModel area) {
        var areas = mongoTemplate.findAll(AreaModel.class);
        var existingArea = areas.stream().filter(x -> Objects.equals(x.getId(), area.getId())).findFirst();
        return existingArea.isPresent();
    }

    public void insertAreaIfDoesntExist(AreaModel area) {
        if (!areaExists(area)) {
            insertArea(area);
        }
    }
    public List<AreaModel> findAreas() {
        var areas = mongoTemplate.findAll(AreaModel.class);
        return areas;
    }
    public Optional<AreaModel> findAreaByName(String nume) {
        var areas = mongoTemplate.findAll(AreaModel.class);
        return areas.stream().filter(x -> x.getName().toLowerCase().contains(nume.toLowerCase())).findFirst();
    }
    public Optional<AreaModel> findAreaByCode(String code) {
        var areas = mongoTemplate.findAll(AreaModel.class);
        return areas.stream().filter(x -> x.getCode().toLowerCase().contains(code.toLowerCase())).findFirst();
    }
}
