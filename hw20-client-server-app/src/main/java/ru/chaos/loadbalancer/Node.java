package ru.chaos.loadbalancer;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Node implements Runnable{
    LoadBalancer loadBalancer;
    private int port;
    Logger logger = LoggerFactory.getLogger(Node.class);


    public Node(LoadBalancer loadBalancer) {
        this.loadBalancer = loadBalancer;
        loadBalancer.register(this);
    }


    public void processRequest() {
        try (ServerSocket serverSocket = new ServerSocket(port);
             Socket clientSocket = serverSocket.accept();
             BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter writer = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()))
        ) {
            String message = reader.readLine();
            writer.println(message + " from Node on port" + port);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
            logger.warn(String.format("Node on port %d was unable to process the request", port));
        }
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void run() {
        while(true) {
            processRequest();
        }
    }

}

