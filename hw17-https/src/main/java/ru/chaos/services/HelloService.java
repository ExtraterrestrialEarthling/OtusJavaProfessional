package ru.chaos.services;

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.apache.hc.client5.http.io.HttpClientConnectionManager;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactory;
import org.apache.hc.core5.ssl.SSLContextBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

@Service
public class HelloService {

    @Value("${server.ssl.trust-store}")
    private Resource trustStore;

    @Value("${server.ssl.trust-store-password}")
    private String trustStorePassword;

    public String sayHelloAdmin(){
        return "Hello, admin!";
    }

    public String sayHelloUser(){
        return "Hello, user!";
    }

    public String sayGoodbye() {
        File truststoreFile = new File("hw17-https", trustStore.getFilename());

        SSLContext sslContext = null;
        try {
            sslContext = new SSLContextBuilder()
                    .loadTrustMaterial(truststoreFile, trustStorePassword.toCharArray())
                    .build();

            SSLConnectionSocketFactory sslConFactory = new SSLConnectionSocketFactory(sslContext);

            HttpClientConnectionManager connectionManager = PoolingHttpClientConnectionManagerBuilder.create()
                    .setSSLSocketFactory(sslConFactory).build();

            CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(connectionManager).build();

            ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);

            RestTemplate template = new RestTemplate(requestFactory);

            ResponseEntity<String> result =
                    template.getForEntity("https://localhost:8182/app/api/v1/goodbye", String.class);
            return result.getBody();
        } catch (NoSuchAlgorithmException | KeyManagementException | KeyStoreException | CertificateException | IOException e) {
            e.printStackTrace();
        }
        return "Что-то пошло не так";
    }
}
