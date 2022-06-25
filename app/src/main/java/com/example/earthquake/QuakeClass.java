package com.example.earthquake;

public class QuakeClass {
    private Float mag;
    private String loaction,time,date,url;

    public QuakeClass(Float mag, String loaction, String date, String time, String url) {
        this.mag = mag;
        this.loaction = loaction;
        this.date = date;
        this.time = time;
        this.url = url;
    }

    public void setMag(Float mag) {
        this.mag = mag;
    }

    public void setLoaction(String loaction) {
        this.loaction = loaction;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Float getMag() {
        return mag;
    }

    public String getLoaction() {
        return loaction;
    }

    public String getTime() {
        return time;
    }

    public String getDate() {
        return date;
    }

    public String getUrl() {
        return url;
    }
}
