package ru.chaos.loadbalancer;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;

public class LoadBalancerConfig {
    private Integer initialNodePort;
    Logger logger = LoggerFactory.getLogger(LoadBalancerConfig.class);

    public LoadBalancerConfig(String propertiesFilename) {
        Properties props = new Properties();
        try {
            props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(propertiesFilename));
        } catch (IOException e) {
            e.printStackTrace();
            logger.warn(String.format("Unable to find properties file with filename %s", propertiesFilename));
        }
        initialNodePort = Integer.parseInt(props.getProperty("loadbalancer.node.initial-port", "8081"));
    }

    public int getInitialNodePort(){
        return initialNodePort;
    }
}
