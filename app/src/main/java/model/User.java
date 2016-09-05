package model;

/**
 * Created by Code Freak Tanveer on 15/08/2016.
 */
public class User {
    private String userName;
    private String password;
    private String fullName;
    private String image;
    private String email;

    public User(String userName, String password, String fullName, String image, String email) {
        this.userName = userName;
        this.password = password;
        this.fullName = fullName;
        this.image = image;
        this.email = email;
    }

    public User() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
