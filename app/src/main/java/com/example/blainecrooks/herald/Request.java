package com.example.blainecrooks.herald;

/**
 * Created by blainecrooks on 16/03/2018.
 */

public class Request {

    String requestID;
    String requestData;
    String roomNumber;
    String requestName;

    public Request(){

    }

    public Request(String requestID, String requestData, String roomNumber, String requestName) {
        this.requestID = requestID;
        this.requestData = requestData;
        this.roomNumber = roomNumber;
        this.requestName = requestName;
    }

    public String getRequestID() {
        return requestID;
    }

    public String getRequestData() {
        return requestData;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public String getRequestName() {
        return requestName;
    }
}
