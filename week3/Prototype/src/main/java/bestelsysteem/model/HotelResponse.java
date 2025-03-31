package bestelsysteem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class HotelResponse {
    boolean status;
    String message;
    long timestamp;
    HotelData data;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public HotelData getData() {
        return data;
    }

    public void setData(HotelData data) {
        this.data = data;
    }

    public HotelResponse() {
    }
}

