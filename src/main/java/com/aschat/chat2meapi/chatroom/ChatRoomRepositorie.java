package com.aschat.chat2meapi.chatroom;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ChatRoomRepositorie extends MongoRepository<ChatRoom , String > {
    Optional<ChatRoom> findBySenderIdAndRecipientID(String senderId, String recipientId);
}
