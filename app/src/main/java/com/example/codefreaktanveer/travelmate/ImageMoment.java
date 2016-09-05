package com.example.codefreaktanveer.travelmate;

/**
 * Created by Nipun on 23-Aug-16.
 */
public class ImageMoment {
    private int moment_id;
    private int event_id;
    private String moment_name;
    private byte[] image;
    private String moment_date_time;

    public ImageMoment() {
    }

    public ImageMoment(int moment_id, int event_id, String moment_name, byte[] image, String moment_date_time) {
        this.moment_id = moment_id;
        this.event_id = event_id;
        this.moment_name = moment_name;
        this.image = image;
        this.moment_date_time = moment_date_time;
    }

    public ImageMoment(int event_id, String moment_name, byte[] image, String moment_date_time) {
        this.event_id = event_id;
        this.moment_name = moment_name;
        this.image = image;
        this.moment_date_time = moment_date_time;
    }

    public int getMoment_id() {
        return moment_id;
    }

    public void setMoment_id(int moment_id) {
        this.moment_id = moment_id;
    }

    public int getEvent_id() {
        return event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    public String getMoment_name() {
        return moment_name;
    }

    public void setMoment_name(String moment_name) {
        this.moment_name = moment_name;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getMoment_date_time() {
        return moment_date_time;
    }

    public void setMoment_date_time(String moment_date_time) {
        this.moment_date_time = moment_date_time;
    }
}
