package data_access.dto;

public class User implements GenericDTO{
    private int id;
    private String name;
    private String card_number;
    private String personal_numerical_code;
    private String address;
    private int login_id;

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
