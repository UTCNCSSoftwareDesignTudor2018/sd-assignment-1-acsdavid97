package data_access.dto;

import java.util.Objects;

public class Login implements GenericDTO{
    private int id;
    private String username;
    private String password;

    public Login() {

    }

    public Login(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Login(Login login) {
        this.username = login.username;
        this.password = login.password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Login login = (Login) o;
        return id == login.id &&
                Objects.equals(username, login.username) &&
                Objects.equals(password, login.password);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, username, password);
    }

    @Override
    public String toString() {
        return "LoginBLL{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
