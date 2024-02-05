package ru.chaos.loadbalancer;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {


        LoadBalancer loadBalancer = new LoadBalancer(new LoadBalancerConfig("application.properties"));
        Thread node1 = new Thread(new Node(loadBalancer));
        Thread node2 = new Thread(new Node(loadBalancer));
        Thread node3 = new Thread(new Node(loadBalancer));
        new Thread(loadBalancer).start();
        node1.start();
        node2.start();
        node3.start();
    }
}

