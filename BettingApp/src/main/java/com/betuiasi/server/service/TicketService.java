package com.betuiasi.server.service;


import com.betuiasi.server.persistence.model.TeamModel;
import com.betuiasi.server.persistence.model.TicketModel;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class TicketService {

    private final MongoTemplate mongoTemplate;
    private final UserService userService;

    @Autowired
    public TicketService(MongoTemplate mongoTemplate, UserService userService) {

        this.mongoTemplate = mongoTemplate;
        this.userService = userService;
    }

    public void insertTicket(TicketModel ticket) {
        mongoTemplate.save(ticket);
    }

    public boolean ticketExists(TicketModel ticket) {
        var tickets = mongoTemplate.findAll(TicketModel.class);
        var existingTicket = tickets.stream().filter(x -> Objects.equals(x.getId(), ticket.getId())).findFirst();
        return existingTicket.isPresent();
    }
    public void insertTicketIfDoenstExist(TicketModel ticket) {
        if (!ticketExists(ticket)) {
            insertTicket(ticket);
            userService.removeFundsById(ticket.getUserUid(), ticket.getBet());
        }
    }

    public TicketModel getTicketById(Long id){
        try {
            var tickets = mongoTemplate.findAll(TicketModel.class);
            var existingTicket = tickets.stream().filter(x -> Objects.equals(x.getId(), id)).findFirst();
            return existingTicket.get();
        } catch (Exception ex) {
            return null;
        }
    }

    public List<TicketModel> getTicketsByUserUid(String userUid){
        try {
            var tickets = mongoTemplate.findAll(TicketModel.class);
            var existingTicket = tickets.stream().filter(x -> Objects.equals(x.getUserUid(), userUid));
            return existingTicket.toList();
        } catch (Exception ex) {
            return null;
        }
    }

    public void clearAll(){
        mongoTemplate.dropCollection(TicketModel.class);
    }

    public boolean updateTicketStatus(String ticketId, String newStatus) {
        try {
            Query query = new Query(Criteria.where("id").is(ticketId));
            Update update = new Update();
            update.set("status", newStatus);
            UpdateResult result = mongoTemplate.updateFirst(query, update, TicketModel.class);
            if(result.getModifiedCount() > 0 && newStatus.equals("CASTIGAT")){
                var tickets = mongoTemplate.findAll(TicketModel.class);
                var existingTicket = tickets.stream().filter(x -> Objects.equals(x.getId(), ticketId)).findFirst();
                if(existingTicket.isPresent()){
                    var win = existingTicket.get().getPotentialWin();
                    var userUid = existingTicket.get().getUserUid();
                    userService.addFundsById(userUid,win);
                }
            }
            return result.getModifiedCount() > 0;
        } catch (Exception ex) {
            System.out.println("Fail to update ticket status: "+ex);
            return false;
        }
    }
}
