package com.example.mychat;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class HelloController {
    TextArea typeMessage;
    ChoiceBox directMessage;
    Button sendButton;
    ListView chatBox;

    public void initialize() {
        System.out.println("CONNECT TO SERVER");
    }

    public void addSelfAsChatter() {
        System.out.println("Identify yourself to Server");
    }

    public void sendMessage() {
        System.out.println("SENT MESSAGE TO ???");
    }

    public void receiveMessage() {

    }

    public void addAnotherChatter() {

    }

}
