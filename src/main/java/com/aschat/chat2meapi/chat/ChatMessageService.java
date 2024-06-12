package com.aschat.chat2meapi.chat;

import com.aschat.chat2meapi.chatroom.ChatRoomRepositorie;
import com.aschat.chat2meapi.chatroom.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.message.Message;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatMessageService {

    private final ChatMessageRepositories chatMessageRepositories;
    private final ChatRoomService chatRoomService;

    public ChatMessage save(ChatMessage  chatMessage){
        var chatId = chatRoomService.getIdByChatRoom(chatMessage.getSenderId(), chatMessage.getRecipientId(), false).orElseThrow();
         chatMessage.setChatId(String.valueOf(chatId));
        chatMessageRepositories.save(chatMessage);

        return chatMessage;
    }

    public List<ChatMessage> findChatMessages(String senderId , String recipientId){
        var chatId = chatRoomService.getIdByChatRoom(senderId, recipientId, false);
        return chatId.map(chatMessageRepositories::findByChatId).orElse(new ArrayList<>());
    }
}
