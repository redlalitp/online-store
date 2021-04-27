package com.lalitpatil.onlinestore.store;

import com.lalitpatil.onlinestore.model.User;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.Set;

@Component
public class UserStore {
    public static Set<User> users = new LinkedHashSet<>();

    public void addUser(User user) {
        this.users.add(user);
    }
}
