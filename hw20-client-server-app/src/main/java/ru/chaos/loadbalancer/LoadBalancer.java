package ru.chaos.loadbalancer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class LoadBalancer implements Runnable {
    private List<Node> registeredNodes;
    private int nextNodePort;
    private Logger logger;
    private int nextNodeIndex;


    public LoadBalancer(LoadBalancerConfig loadBalancerConfig) {
        this.registeredNodes = Collections.synchronizedList(new ArrayList<>());
        nextNodePort = loadBalancerConfig.getInitialNodePort();
        nextNodeIndex = 0;
        logger = LoggerFactory.getLogger(LoadBalancer.class);
    }

    public void run() {
        if (registeredNodes.isEmpty()) {
            logger.warn("No nodes registered");
            throw new IllegalStateException("No nodes registered.");
        } else {
            while (true) {
                Node node = nextNode();
                try (Socket server = new Socket("localhost", node.getPort());
                     BufferedReader reader = new BufferedReader(new InputStreamReader(server.getInputStream()));
                     PrintWriter writer = new PrintWriter(new OutputStreamWriter(server.getOutputStream()))) {
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("Say something...");
                    String message = scanner.nextLine();
                    writer.println(message);
                    writer.flush();
                    System.out.println(reader.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                    logger.warn("Node is not responding. It will be removed from the registry...");
                    registeredNodes.remove(node);
                }
            }
        }
    }


    public void register(Node node) {
        node.setPort(getNextNodePort());
        registeredNodes.add(node);
    }


    private Node nextNode() {
        Node node = registeredNodes.get(nextNodeIndex);
        nextNodeIndex = nextNodeIndex == registeredNodes.size() - 1 ? 0 : nextNodeIndex + 1;
        return node;
    }

    private int getNextNodePort() {
        return nextNodePort++;
    }


}
