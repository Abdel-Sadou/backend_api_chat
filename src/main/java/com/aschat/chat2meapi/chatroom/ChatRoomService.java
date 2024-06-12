package com.aschat.chat2meapi.chatroom;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatRoomService {

    private  final ChatRoomRepositorie chatRoomRepositorie ;

    public Optional<String> getIdByChatRoom(String senderId , String recipientId, Boolean createNewRoomIfExists){

        chatRoomRepositorie.findBySenderIdAndRecipientID(senderId , recipientId).
                map(ChatRoom::getChatID)
                .or(()->{
                    if (createNewRoomIfExists){
                        var chatId = createChatId(senderId,recipientId);
                        return Optional.of(chatId);
                    }
                    return Optional.empty();
                });

        return Optional.empty();
    }

    private String createChatId(String senderId, String recipientId) {
        String chatId = String.format("%s_%s", senderId,recipientId);
        ChatRoom chatRoomSenderRecipient  = ChatRoom.builder()
                .chatID(chatId)
                .senderId(senderId)
                .recipientID(recipientId)
                .build();

  ChatRoom chatRoomRecipientSender  = ChatRoom.builder()
                .chatID(chatId)
                .senderId(recipientId)
                .recipientID(senderId)
                .build();
        chatRoomRepositorie.save(chatRoomRecipientSender);
        chatRoomRepositorie.save(chatRoomSenderRecipient);

        return chatId;
    }
}
