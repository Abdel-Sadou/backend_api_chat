package com.aschat.chat2meapi.user;


import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@Builder
public class User {
    @Id
    private String nickname;
    private String firstname;
    private String lastname;
    private Status status;
}
