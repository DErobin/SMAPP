package derobin.smapp.User;

/**
 * Created by Robin on 31-3-2016.
 */
public class Profile {
    private String token;
    private String name;
    private String email;
    private String gender;
    private String birthday;
    public Profile(String token, String name, String email, String gender, String birthday) {
        this.token = token;
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return name + " " + email + " " + gender + " " + birthday;
    }
}
