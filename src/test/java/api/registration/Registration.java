package api.registration;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Registration {
    private String email;
    private String password;

    public Registration(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Registration(String email) {
        this.email = email;
    }

    public Registration() {
    }

}
