package com.aschat.chat2meapi.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final  UserService userService;

    @MessageMapping("/user.saveUser")
    @SendToUser("/user/topic")//envoie a toutes le mondes qui est dans la fille d'attente user/topic
    public User  save( @Payload User user){
        userService.save(user);
        return user;
    }


    @MessageMapping("/user.disconnect.User")
    @SendToUser("/user/topic")
    public User disconnect( @Payload  User user){
          userService.disconnect(user);
          return user;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> findAllUsersConnected(){
        return ResponseEntity.ok(userService.findAllConnected());
    }

}
