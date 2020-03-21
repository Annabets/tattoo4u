package models;

public class SignInUserModel {

    private String name;
    private String password;

    public SignInUserModel(String name, String password){
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}
