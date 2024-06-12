package com.aschat.chat2meapi.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private  final  UserRepositorie userRepositorie;
    public void save(User user){
        user.setStatus(Status.ONLINE);
        userRepositorie.save(user);
    }

    public void disconnect(User user){
       Optional<User> storeUser = userRepositorie.findById(user.getNickname());
        storeUser.ifPresent(u-> {
            u.setStatus(Status.OFFLINE);
            userRepositorie.save(u);
        });
    }

    public List<User> findAllConnected(){
        return      userRepositorie.findAllByStatus(Status.ONLINE);
    }
}
