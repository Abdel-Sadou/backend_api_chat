package com.aschat.chat2meapi.chat;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ChatMessageRepositories extends MongoRepository<ChatMessage , String> {

    public List<ChatMessage> findByChatId(String  chatId);
}
