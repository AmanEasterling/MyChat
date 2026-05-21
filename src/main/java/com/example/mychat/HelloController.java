package com.example.mychat;


import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class HelloController {
    public TextArea typeMessage;
    public ChoiceBox directMessage;
    public Button sendButton;
    public ListView chatBox;

    ObjectOutputStream myObjOutput;
    ObjectInputStream myObjInput;
    String newName;

    public void initialize() throws IOException {

        System.out.println("CONNECT TO SERVER");

        // Connect to server running on SAME 127.0.0.1 computer
        Socket ourSocket = new Socket("127.0.0.1", 12345);

        // Client MUST create InputStream BEFORE OutputStream!!!!!!
        myObjOutput = new ObjectOutputStream(ourSocket.getOutputStream());
        myObjInput = new ObjectInputStream(ourSocket.getInputStream());
        CommunicationConnection newConnection = new CommunicationConnection("Mr. H",ourSocket,myObjInput,myObjOutput);
        CommunicationIn myCommunicationIn = new CommunicationIn(newConnection, false);
        Thread communicationInThread = new Thread(myCommunicationIn);
        communicationInThread.start();

    }

    public void addSelfAsChatter() {
        System.out.println("Identify yourself to Server");
    }

    public void changName() throws IOException {
        newName = "???"; // FINISH
        Message message1 = new Message(1,1,"", newName, "SERVER");
        myObjOutput.writeObject(message1);
        myObjOutput.flush();

    }

    public void sendMessage() throws IOException {
        String theText = "?????"; // FINISH
        System.out.println("SENT MESSAGE TO ???");
        Message message1 = new Message(1,1,theText,newName, "SERVER");
        myObjOutput.writeObject(message1);
        myObjOutput.flush();
    }

    public void receiveMessage() {System.out.println("");

    }

    public void addAnotherChatter() {System.out.println("");

    }

}
