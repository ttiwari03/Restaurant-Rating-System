package com.example.demo.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import com.example.demo.entities.User;

public class UserRepository implements CrudRepository<User, Long> {
    private final Map<Long, User> userMap;
    private Long autoIncrementId;

    public UserRepository() {
        userMap = new HashMap<>();
        autoIncrementId = 1L;
    }

    @Override
    public User save(User user) {
        User newUser = new User(autoIncrementId, user.getName());
        userMap.put(autoIncrementId, newUser);
        autoIncrementId++;
        return newUser;
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(userMap.get(id));
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(userMap.values());
    }

    @Override
    public void delete(User user) {
        if (userMap.containsValue(user)) {
            deleteById(user.getId());
        }
    }

    @Override
    public void deleteById(Long id) {
        userMap.remove(id);
    }

    @Override
    public Long count() {
        return (long) userMap.size();
    }

    @Override
    public Boolean existById(Long id) {
        return userMap.containsKey(id);
    }
    
}
