package gr.aueb.supermarket.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
@Embeddable
public class UserInfo {
    @Column(name = "username", length = 60, nullable = false)
    private String username;
    @Column(name = "password", length = 60, nullable = false)
    private String password;

    public UserInfo(){

    }

    public UserInfo(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getters Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
