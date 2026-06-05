package com.example.mychat;


import javafx.scene.control.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class HelloController {
    public TextArea typeMessage;
    public TextField changNameText;
    public ChoiceBox<String> directMessage;
    public Button sendButton;
    public ListView<String> chatBox;

    ObjectOutputStream myObjOutput;
    ObjectInputStream myObjInput;
    String newName;

    public void initialize() throws IOException {

        System.out.println("CONNECT TO SERVER: " + this);

        // Connect to server running on SAME 127.0.0.1 computer
        Socket ourSocket = new Socket("127.0.0.1", 12345);

        // Client MUST create InputStream BEFORE OutputStream!!!!!!
        myObjOutput = new ObjectOutputStream(ourSocket.getOutputStream());
        myObjInput = new ObjectInputStream(ourSocket.getInputStream());
        CommunicationConnection newConnection = new CommunicationConnection("Mr. H",ourSocket,myObjInput,myObjOutput);
        CommunicationIn myCommunicationIn = new CommunicationIn(newConnection, false, this);
        Thread communicationInThread = new Thread(myCommunicationIn);
        communicationInThread.start();
    }

    public void addSelfAsChatter(String selfChatter) {

        System.out.println("addAnotherChatter: " + selfChatter);
    }

    public void changName() throws IOException {
        newName = changNameText.getText(); // FINISH
        Message message1 = new Message(1,1,"", newName, "SERVER");
        myObjOutput.writeObject(message1);
        myObjOutput.flush();

    }

    public void sendMessage() throws IOException {
        String theText = typeMessage.getText(); // FINISH
        // Get the to name from the choice box:
       // = directMessage.getValue();
        Message message1 = new Message(1,2,theText,newName, directMessage.getValue());
        System.out.println("sendMessage: " + message1);
        myObjOutput.writeObject(message1);
        myObjOutput.flush();

    }

    public void receiveMessage(Message message) throws IOException {
        System.out.println("receiveMessage: " + message);
        // display PRETTY message in the ListView
        String from = message.from;
        String whatIsMessage = message.text;
        String prettyMessage = from + " said: " + whatIsMessage;
        chatBox.getItems().add(prettyMessage);
    }

    public void addAnotherChatter(String newChatter) {
        System.out.println("addAnotherChatter: " + newChatter);
        directMessage.getItems().add(newChatter);

    }

}
