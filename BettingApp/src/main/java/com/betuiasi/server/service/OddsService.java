package com.betuiasi.server.service;

import com.betuiasi.server.persistence.model.MatchModel;
import com.betuiasi.server.persistence.model.OddsModel;
import com.betuiasi.server.persistence.model.PlayerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;
import java.util.Random;

@Service
public class OddsService {
    private final MongoTemplate mongoTemplate;

    @Autowired
    public OddsService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public OddsModel generateOdds(){
        Random random = new Random();
        HashMap<String, Double> generatedOdds = new HashMap<>();
        double cota1 = 1.6+(random.nextDouble()*(3-1.6));
        double cotaX = 1.6+(random.nextDouble()*(3-1.6));
        double cota2 = 1.6+(random.nextDouble()*(3-1.6));

        //Clasic
        generatedOdds.put("1",cota1);
        generatedOdds.put("X",cotaX);
        generatedOdds.put("2",cota2);

        //Sansa dubla
        generatedOdds.put("1X",(cota1+cotaX)/2);
        generatedOdds.put("X2",(cota2+cotaX)/2);
        generatedOdds.put("12",(cota1+cota2)/2);

        //Ambele marcheaza sau nu
        generatedOdds.put("GG",(cota1+cota2+cotaX)/3);
        generatedOdds.put("NGG", 0.85*(cota1+cota2+cotaX)/3);

        //Pauza sau final
        generatedOdds.put("PsF1",0.8*cota1);
        generatedOdds.put("PsFX",0.7*cotaX);
        generatedOdds.put("PsF2",0.75*cota2);

        //Nr. goluri marcate
        generatedOdds.put("+5,5",2.30*Math.max(cota1, cota2));
        generatedOdds.put("+4,5",1.80*Math.max(cota1, cota2));
        generatedOdds.put("+3,5",1.20*Math.max(cota1, cota2));
        generatedOdds.put("+2,5",0.98*Math.max(cota1, cota2));
        generatedOdds.put("+1,5",0.5*Math.max(cota1, cota2));
        generatedOdds.put("+0,5",0.25*Math.max(cota1, cota2));

        generatedOdds.put("-5,5",0.2*Math.max(cota1, cota2));
        generatedOdds.put("-4,5",0.4*Math.max(cota1, cota2));
        generatedOdds.put("-3,5",0.6*Math.max(cota1, cota2));
        generatedOdds.put("-2,5",0.98*Math.max(cota1, cota2));
        generatedOdds.put("-1,5",1.2*Math.max(cota1, cota2));
        generatedOdds.put("-0,5",1.5*Math.max(cota1, cota2));

        //Pauza/Final
        generatedOdds.put("1/1",0.8*cota1);
        generatedOdds.put("1/X",1.2*cota1);
        generatedOdds.put("1/2",12*Math.max(cota1, cota2));
        generatedOdds.put("X/1",1.3*Math.max(cotaX, cota1));
        generatedOdds.put("X/X",0.9*cotaX);
        generatedOdds.put("X/2",1.4*Math.max(cotaX, cota2));
        generatedOdds.put("2/1",24*Math.max(cota1, cota2));
        generatedOdds.put("2/X",3*Math.max(cotaX, cota2));
        generatedOdds.put("2/2",0.95*cota2);

        return new OddsModel(generatedOdds);
    }

    public void insertOddsIfDoesntExist() {
            insertOdds(generateOdds());

    }
    public void insertOdds(OddsModel odds) {
        mongoTemplate.save(odds);
    }

}
