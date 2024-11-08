package ac.ku.Lorna.models;

public class User {
    private long userId;
    private String username;
    private String role;

    public User(long userId, String username, String role) {
        this.userId = userId;
        this.username = username;
        this.role = role;
    }

    public long getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }
}
