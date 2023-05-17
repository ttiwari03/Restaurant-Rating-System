package com.example.demo.entities;

public class User {
    private Long id;
    private String name;
    private UserLevel level;
    private Long numberOfRatings;
    
    public User(Long id, String name) {
        this.id = id;
        this.name = name;
        this.numberOfRatings = 0L;
    }

    public User(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public UserLevel getLevel() {
        return level;
    }

    public Long getNumberOfRatings() {
        return numberOfRatings;
    }

    public void incrementRatingCount() {
        this.numberOfRatings++;
        setUserLevel();
    }

    private void setUserLevel() {}

    @Override
    public String toString() {
        return "User [id=" + id + "]";
    }    
}
