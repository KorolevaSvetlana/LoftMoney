package selina.com;

import com.google.gson.annotations.SerializedName;

public class Status {

    private String status;

    private int id;

    @SerializedName("auth_token")
    private String token;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }
}
