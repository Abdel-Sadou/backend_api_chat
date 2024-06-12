package com.aschat.chat2meapi.chatroom;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Data
@Document
public class ChatRoom {
    @Id
    private String id;
    private String chatID;
    private String senderId;
    private String recipientID;
}
