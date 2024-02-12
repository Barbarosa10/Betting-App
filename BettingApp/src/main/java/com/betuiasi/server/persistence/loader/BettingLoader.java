package com.betuiasi.server.persistence.loader;


import java.io.IOException;
import java.net.*;
import java.net.http.*;
import java.util.List;

import com.betuiasi.server.persistence.model.*;
import com.betuiasi.server.service.*;
import com.fasterxml.jackson.databind.ObjectMapper;


public class BettingLoader {

    private final HttpClient client = HttpClient.newHttpClient();
    private final HttpRequest request;

    private final AreaService areaService;
    private final MatchService matchService;
    private final CompetitionService competitionService;
    private final TeamService teamService;
    private final OddsService oddsService;
    private final SeasonService seasonService;

    private final ScoreService scoreService;


    public BettingLoader(AreaService areaService, MatchService matchService, CompetitionService competitionService, TeamService teamService, SeasonService seasonService, ScoreService scoreService, OddsService oddsService) {
        this.areaService = areaService;
        this.matchService = matchService;
        this.competitionService = competitionService;
        this.seasonService = seasonService;
        this.teamService = teamService;
        this.scoreService = scoreService;
        this.oddsService = oddsService;
        this.request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.football-data.org/v4/matches"))
                .header("X-Auth-Token", "1d7d8af00e18408c903bb4c5f7e09db4")
                .build();
    }

    public void populateArea(AreaService areaService, AreaModel area) {
        try {
            areaService.insertAreaIfDoesntExist(area);
        } catch (Exception ignored) {
        }
    }

    public void populateCompetition(CompetitionService competitionService, CompetitionModel competition) {
        try {
            competitionService.insertCompetitionIfDoenstExist(competition);
        } catch (Exception ignored) {
        }
    }

    public void populateTeam(TeamService teamService, TeamModel team){
        teamService.clearAll();
        try {
            teamService.insertTeamIfDoenstExist(team);
        } catch (Exception ignored) {
        }
    }

    public void populateScore(ScoreService scoreService, ScoreModel score){
        scoreService.clearAll();
        try {
            scoreService.insertScore(score);
        } catch (Exception ignored) {
        }
    }

    public void loadMatches() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.football-data.org/v4/matches"))
                .header("X-Auth-Token", "1d7d8af00e18408c903bb4c5f7e09db4")
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        //System.out.println(response.body());
        ObjectMapper objectMapper = new ObjectMapper();
        MatchesModel matches = objectMapper.readValue(response.body(), MatchesModel.class);

        HttpRequest requestTeams = HttpRequest.newBuilder()
                .uri(URI.create("https://api.football-data.org/v4/teams"))
                .header("X-Auth-Token", "1d7d8af00e18408c903bb4c5f7e09db4")
                .build();
        HttpResponse<String> responseTeams = client.send(requestTeams,HttpResponse.BodyHandlers.ofString());
        //System.out.println(responseTeams.body());
        ObjectMapper objectMapperTeams = new ObjectMapper();
        TeamsModel teams = objectMapperTeams.readValue(responseTeams.body(), TeamsModel.class);



        for (TeamModel team:teams.getTeams()){
            populateTeam(teamService,team);
        }


        for (MatchModel match : matches.getMatches()) {
            populateArea(areaService, match.getArea());
            populateCompetition(competitionService, match.getCompetition());
            seasonService.insertSeasonIfDoenstExist(match.getSeason());
            match.setOdds_generated(oddsService.generateOdds());
            System.out.println(match.getOdds_generated().getOdds().toString());
            populateScore(scoreService, match.getScore());
            matchService.insertMatchIfNotExist(match);

        }
    }
}