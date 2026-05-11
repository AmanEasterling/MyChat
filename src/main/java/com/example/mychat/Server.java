package com.example.mychat;

import java.util.ArrayList;

public class Server {
    public static AlexQueue theQueue = new AlexQueue();
    static ArrayList<CommunicationConnection> allConnections = new ArrayList<>();

    public static void main(String[] args)  {
        ServerConnector myServerConnector =  new ServerConnector();
        Thread myServerConnectorThread = new Thread(myServerConnector);
        myServerConnectorThread.start();

        CommunicationOut myCommunicationOut = new CommunicationOut();
        Thread myCommunicationOutThread = new Thread(myCommunicationOut);
        myCommunicationOutThread.start();
    }
}