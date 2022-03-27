package ru.nsu.ccfit.kondakova.xmlMessages;

public class ClientLoginMessage extends Message {
    private String userName;

    public ClientLoginMessage(String userName) {
        super("login");
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }
}