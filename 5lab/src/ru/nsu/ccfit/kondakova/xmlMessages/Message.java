package ru.nsu.ccfit.kondakova.xmlMessages;

public class Message {
    private final String type;

    public Message(String type) {
        this.type = type;
    }

    public String getType(){
        return type;
    }
}