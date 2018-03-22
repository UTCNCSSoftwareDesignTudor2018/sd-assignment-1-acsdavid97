package data_access.dto;

import java.util.Objects;

public class User implements GenericDTO{
    private int id;
    private String name;
    private String card_number;
    private String personal_numerical_code;
    private String address;
    private int login_id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                login_id == user.login_id &&
                Objects.equals(name, user.name) &&
                Objects.equals(card_number, user.card_number) &&
                Objects.equals(personal_numerical_code, user.personal_numerical_code) &&
                Objects.equals(address, user.address);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, card_number, personal_numerical_code, address, login_id);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", card_number='" + card_number + '\'' +
                ", personal_numerical_code='" + personal_numerical_code + '\'' +
                ", address='" + address + '\'' +
                ", login_id=" + login_id +
                '}';
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCard_number() {
        return card_number;
    }

    public void setCard_number(String card_number) {
        this.card_number = card_number;
    }

    public String getPersonal_numerical_code() {
        return personal_numerical_code;
    }

    public void setPersonal_numerical_code(String personal_numerical_code) {
        this.personal_numerical_code = personal_numerical_code;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getLogin_id() {
        return login_id;
    }

    public void setLogin_id(int login_id) {
        this.login_id = login_id;
    }
}
