package com.aschat.chat2meapi.chat;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@AllArgsConstructor
@Data
@Builder
public class ChatNotification {
    private  String id;
    private  String senderId;
    private  String recipientId;
    private  String content;

}
