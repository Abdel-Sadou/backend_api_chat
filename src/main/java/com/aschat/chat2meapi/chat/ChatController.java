package com.aschat.chat2meapi.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final ChatMessageService chatMessageService;
    private  final  SimpMessagingTemplate messagingTemplate;


    @MessageMapping("/chat")
    public void progressMessage ( @Payload ChatMessage chatMessage){
       ChatMessage savedMessage  = chatMessageService.save(chatMessage);
        messagingTemplate.convertAndSendToUser(savedMessage.getRecipientId(),
                "/queue/messages", ChatNotification.builder()
                                .id(savedMessage.getChatId())
                                .senderId(savedMessage.getSenderId())
                                .recipientId(savedMessage.getRecipientId())
                                .content(savedMessage.getContent())
                        .build()
        );
    }

    @GetMapping("/messages/{senderId}/{recipientId}")
    public ResponseEntity< List<ChatMessage>> findChatMessages (@PathVariable String senderId ,
                                                                @PathVariable String recipientId){

        return  ResponseEntity.ok(chatMessageService.findChatMessages(senderId , recipientId));
    }


}
