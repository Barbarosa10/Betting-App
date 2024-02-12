package com.betuiasi.server.Component;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import com.betuiasi.server.persistence.loader.BettingLoader;
import com.betuiasi.server.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTask {
    @Autowired
    AreaService areaService;
    @Autowired
    MatchService matchService;
    @Autowired
    CompetitionService competitionService;
    @Autowired
    TeamService teamService;
    @Autowired
    SeasonService seasonService;
    @Autowired
    ScoreService scoreService;

    @Autowired
    OddsService oddsService;
    private static final Logger log = LoggerFactory.getLogger(ScheduledTask.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 6000)
    public void ScheduleTask() {
        log.info("The time is now {}", dateFormat.format(new Date()));

        // Run BettingLoader in a separate thread that refreshes every 60 seconds
        runBettingLoader(areaService,matchService,competitionService,teamService,seasonService,scoreService,oddsService);
    }
    private static void runBettingLoader(AreaService areaService, MatchService matchService,CompetitionService competitionService,TeamService teamService, SeasonService seasonService, ScoreService scoreService, OddsService oddsService) {
            try {

                BettingLoader bettingLoader = new BettingLoader(areaService,matchService,competitionService,teamService,seasonService, scoreService, oddsService);
                bettingLoader.loadMatches();
                System.out.println("Pulled data from API at " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss")));

            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
    }
}