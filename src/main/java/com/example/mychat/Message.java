package com.example.mychat;

import java.io.Serializable;

public class Message implements Serializable {
    private static final long serialVersionUID = 1234567L;
    Integer version = 1;
    Integer mode;
    // 1: START
    // 2: COMMUNICATE
    // 3: STOP
    String text;
    String to;
    String from;
    //LocalDate timeStamp;
    //Media voice;
    //Image picture;

    public Message(Integer version, Integer mode, String text, String from, String to) {
        this.version = version;
        this.mode = mode;
        this.text = text;
        this.from = from;
        this.to = to;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getMode() {
        return mode;
    }

    public void setMode(Integer mode) {
        this.mode = mode;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    @Override
    public String toString() {
        return "Message{" +
                "version=" + version +
                ", mode=" + mode +
                ", text='" + text + '\'' +
                ", to='" + to + '\'' +
                ", from='" + from + '\'' +
                '}';
    }
}
