package com.betuiasi.server.controller;

import com.betuiasi.server.persistence.model.SeasonModel;
import com.betuiasi.server.persistence.model.TicketModel;
import com.betuiasi.server.persistence.model.TicketRequestModel;
import com.betuiasi.server.persistence.model.UpdateTicketModel;
import com.betuiasi.server.service.SeasonService;
import com.betuiasi.server.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/ticket")
public class TicketController {
    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/{userUid}")
    public ResponseEntity<?> getTickets(@PathVariable String userUid){
        List<TicketModel> foundUserTickets = ticketService.getTicketsByUserUid(userUid);
        return foundUserTickets.isEmpty() ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                new ResponseEntity<>(foundUserTickets, HttpStatus.OK);
    }

    @PostMapping(value="",consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> postTicket(@RequestBody TicketRequestModel ticketRequest){
        try {
            var ticketModel = new TicketModel(
                    UUID.randomUUID().toString(),
                    ticketRequest.getUserUid(),
                    Instant.now().toString(),
                    ticketRequest.getMatches(),
                    ticketRequest.getBet(),
                    ticketRequest.getPotentialWin(),
                    "IN ASTEPTARE"
            );
            ticketService.insertTicketIfDoenstExist(ticketModel);
            return ResponseEntity.status(HttpStatus.OK).body("");
        } catch (Exception e)  {
            System.out.println("Fail to post ticket. "+ e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
        }
    }

    @PostMapping(value="/update",consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> updateTicketStatus(@RequestBody UpdateTicketModel updateTicketModel){
        try {
            ticketService.updateTicketStatus(updateTicketModel.getTicketId(), updateTicketModel.getNewStatus());
            return ResponseEntity.status(HttpStatus.OK).body("");
        } catch (Exception e)  {
            System.out.println("Fail to update ticket status. "+ e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
        }
    }

}
