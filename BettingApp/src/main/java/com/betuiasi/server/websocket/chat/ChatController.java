package com.betuiasi.server.websocket.chat;

import com.betuiasi.server.websocket.chat.model.ChatMessageDataModel;
import com.betuiasi.server.websocket.chat.model.ChatMessageModelMapper;
import com.betuiasi.server.websocket.chat.model.ChatMessageUIModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;


@Controller
public class ChatController {

    private final ChatService chatService;
    @Autowired
    public ChatController(ChatService chatService){
        this.chatService = chatService;
    }

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessageUIModel sendMessage(
            @Payload ChatMessageUIModel chatMessage
    ) {
        saveChatMessage(chatMessage);
        return chatMessage;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public List<ChatMessageUIModel> addUser(
            @Payload ChatMessageUIModel chatMessage,
            SimpMessageHeaderAccessor headerAccessor
    ) {
        headerAccessor.getSessionAttributes().put("username", chatMessage.getUsername());
        return getLatestMessages();
    }

    private void saveChatMessage(ChatMessageUIModel message) {
        ChatMessageDataModel messageDataModel = ChatMessageModelMapper.toDataModel(message);
        chatService.insertChatMessage(messageDataModel);
    }

    private List<ChatMessageUIModel> getLatestMessages() {
        return new ArrayList<>();
        //return chatService.getLatestMessages().stream().map(ChatMessageModelMapper::toUiModel).toList();
    }

}
