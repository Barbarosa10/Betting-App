package com.betuiasi.server.websocket.chat.model;

import java.time.Instant;
import java.util.Date;

public class ChatMessageModelMapper {
    public static ChatMessageUIModel toUiModel(ChatMessageDataModel messageDataModel) {
        ChatMessageUIModel messageUIModel = new ChatMessageUIModel();
        messageUIModel.setUsername(messageUIModel.username);
        messageUIModel.setMessageBody(messageUIModel.messageBody);
        return messageUIModel;
    }

    public static ChatMessageDataModel toDataModel(ChatMessageUIModel messageUIModel) {
        ChatMessageDataModel messageDataModel = new ChatMessageDataModel();
        messageDataModel.setUsername(messageUIModel.username);
        messageDataModel.setMessageBody(messageUIModel.messageBody);
        messageDataModel.setTimestamp(Date.from(Instant.now()).getTime());
        return messageDataModel;
    }
}
