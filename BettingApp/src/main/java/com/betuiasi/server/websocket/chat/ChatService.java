package com.betuiasi.server.websocket.chat;

import com.betuiasi.server.websocket.chat.model.ChatMessageDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {
    private int MAX_MESSAGES = 50;

    private final MongoTemplate mongoTemplate;

    @Autowired
    public ChatService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public void insertChatMessage(ChatMessageDataModel message) {
        mongoTemplate.save(message);
    }

    public List<ChatMessageDataModel> getLatestMessages() {
        Query query = new Query();
        query.with(Sort.by(Sort.Direction.DESC, "timestamp")).limit(MAX_MESSAGES);
        return mongoTemplate.find(query, ChatMessageDataModel.class);
    }
}
