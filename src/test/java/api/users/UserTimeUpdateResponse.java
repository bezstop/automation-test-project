package api.users;


public class UserTimeUpdateResponse extends UserTimeUpdateRequest {
    private String updatedAt;

    public UserTimeUpdateResponse() {
    }

    public UserTimeUpdateResponse(String name, String job, String updatedAt) {
        super(name, job);
        this.updatedAt = updatedAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }
}
