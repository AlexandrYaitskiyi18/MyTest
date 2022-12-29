package VideoGames;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public class StatusPost {
    public String status;
    //@JsonProperty("timestamp")
    //private Instant timeStamp;
    public StatusPost() {
    }

    public StatusPost(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
