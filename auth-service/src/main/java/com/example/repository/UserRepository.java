package com.example.repository;

import com.example.entity.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class UserRepository {

    private Map<String, User> map = new HashMap<>();

    public void save(User user) {
        map.put(user.getName(), user);
    }

    public Optional<User> getUserByName(String name) {
        return Optional.ofNullable(map.get(name));
    }
}
