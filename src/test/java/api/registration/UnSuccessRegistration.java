package api.registration;

public class UnSuccessRegistration {
    private String error;

    public UnSuccessRegistration() {
    }

    public UnSuccessRegistration(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
