package main.beans;

import main.entity.User;

public class UserBeans {
    private long id;
    private String authority;
    private String displayName;
    private String password;
    private String username;
    private String basyo;

    public UserBeans() {
    }

    public UserBeans(User user) {
        this.displayName = "Admin";
        this.password = user.getPassword();
        this.username = user.getUserName();
        this.id = user.getId();
        this.authority = user.getAuthority();
    }

    public UserBeans(long id, String authority, String displayName, String password, String username, String basyo) {
        this.id = id;
        this.authority = authority;
        this.displayName = displayName;
        this.password = password;
        this.username = username;
        this.basyo = basyo;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBasyo() {
        return basyo;
    }

    public void setBasyo(String basyo) {
        this.basyo = basyo;
    }
}
