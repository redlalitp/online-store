package com.lalitpatil.onlinestore.util;

import com.lalitpatil.onlinestore.model.User;
import com.lalitpatil.onlinestore.store.UserStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserOperations {
    @Autowired
    UserStore userStore;
    public User getUserById(long id) {
        User user = null;
        for(User existingUser : this.userStore.getUsers()) {
            if(existingUser.getId() == id) {
                user = existingUser;
                break;
            }
        }
        return user;
    }
}
