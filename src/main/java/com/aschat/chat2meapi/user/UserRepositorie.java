package com.aschat.chat2meapi.user;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepositorie extends MongoRepository<User, String> {

    public List<User> findAllByStatus(Status status);

}
