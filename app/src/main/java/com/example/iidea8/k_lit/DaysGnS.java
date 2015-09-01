package com.example.iidea8.k_lit;

/**
 * Created by Abhigyan on 8/10/2015.
 */
public class DaysGnS {

    private String eventName;
    private String subEventName;
    private String time;
    private StringBuilder moderator;
    private StringBuilder speakers;


    public DaysGnS(){
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getSubEventName() {
        return subEventName;
    }

    public void setSubEventName(String subEventName) {
        this.subEventName = subEventName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public StringBuilder getModerator() {
        return moderator;
    }

    public void setModerator(StringBuilder moderator) {
        this.moderator = moderator;
    }

    public StringBuilder getSpeakers() {
        return speakers;
    }

    public void setSpeakers(StringBuilder speakers) {
        this.speakers = speakers;
    }


}
