package model;

public class User {
    private int id;
    private String name;
    private String username;
    private String email;
    private Address address;

    public User () {

    }

    public User (String name, String username, String email) {
        this.id = 99;
        this.name = name;
        this.username = username;
        this.email = email;
        this.address = new Address();
    }

    public String getName() {
        return name;
    }

    public String getUserName() {
        return username;
    }
}
