package com.example.blainecrooks.herald;

/**
 * Created by blainecrooks on 17/03/2018.
 */

public class RequestInformation {

    private String name;
    private String request;
    private String room;

    public RequestInformation() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }
}
