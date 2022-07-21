package model;

public class User {
    private int id;
    private String name;
    private String username;
    private String email;

    public User () {

    }

    public User (String name, String username, String email) {
        this.name = name;
        this.username = username;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }
}
