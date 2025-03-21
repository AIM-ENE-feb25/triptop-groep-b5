package com.example.demo.controller;

import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RestController
@RequestMapping("/api")
public class UberController {

    @Value("${rapidapi.key}")
    private String rapidApiKey;

    @GetMapping("/uber")
    public ResponseEntity<String> getUber() throws IOException {

        try {
            AsyncHttpClient client = new DefaultAsyncHttpClient();
            client.prepare("GET", "https://t14ha70d-uber-v1.p.rapidapi.com/v1/products")
                    .setHeader("x-rapidapi-key", rapidApiKey)
                    .setHeader("x-rapidapi-host", "t14ha70d-uber-v1.p.rapidapi.com")
                    .execute()
                    .toCompletableFuture()
                    .thenAccept(System.out::println)
                    .join();

            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok("Uber API called successfully");
    }
}
