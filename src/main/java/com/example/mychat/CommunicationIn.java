package com.example.mychat;

import javafx.application.Platform;

import java.io.IOException;

public class CommunicationIn implements Runnable {
    CommunicationConnection myConnection;
    HelloController myController;
    boolean isServer;

    public CommunicationIn(CommunicationConnection connection, boolean isServer, HelloController controller) {
        this.myConnection = connection;
        this.isServer = isServer;
        this.myController = controller;
    }

    @Override
    public void run() {
        boolean stayConnected = true;
        while (stayConnected && !Thread.currentThread().isInterrupted()) {
            Message newMessage = null;
            try {
                newMessage = (Message)myConnection.getInStream().readObject();
            } catch (Exception ex) {
                System.out.println("CommunicationIn failed connection with:" + myConnection.getName() + ": " + ex);
            }

            if (newMessage != null) {
                System.out.println("CommunicationIn from: " + myConnection.getName() + ": " + newMessage);
                if (isServer) {
                    if (newMessage.mode == 1) {
                        // START
                        // associate FROM name with its socket
                        myConnection.setName(newMessage.from);
                        newMessage = new Message(1,1,"Welcome: " + newMessage.from, newMessage.from, "ALL");
                    } else if (newMessage.mode == 2) {
                        // COMMUNICATE
                        newMessage = newMessage;
                    } else if (newMessage.mode == 3) {
                        // STOP
                        newMessage = new Message(1,3,"Goodbye: " + newMessage.from, newMessage.from,"ALL");
                        stayConnected = false;
                    }
                    boolean putSuccess  = Server.theQueue.put(newMessage);
                    while (!putSuccess) {
                        putSuccess  = Server.theQueue.put(newMessage);
                    }
                } else {
                    if (newMessage.mode == 1) {
                        // START
                        // add FROM name to chatters list
                        Message NewMessage = newMessage;
                        Platform.runLater(() -> {

                            myController.addAnotherChatter(NewMessage.from);

                        });

                    } else if (newMessage.mode == 2) {
                        // tell the Controller to add the message to the ListView
                        Message finalNewMessage = newMessage;
                        Platform.runLater(() -> {
                            try {
                                myController.receiveMessage(finalNewMessage);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }

                        });

                    } else if (newMessage.mode == 3) {
                        // STOP
                        // remove FROM name to chatters list

                        //newMessage = new Message(1,3,"Goodbye: " + newMessage.from, "SERVER", newMessage.from);
                        //stayConnected = false;
                    }
                }
            }
        }

        System.out.println("CommunicationIn bye: " + myConnection.getName());
    }
}

