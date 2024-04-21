package com.luma.api;

import okhttp3.*;

import java.io.*;
import java.util.Properties;
import java.util.Scanner;

public class MagentoApi {

    private static final String BASE_URL = "https://magento.softwaretestingboard.com/rest/default/V1/";
    private static final String TOKEN_ENDPOINT = "integration/customer/token/";
    private static final String CUSTOMER_ENDPOINT = "customers/me";
    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    private final OkHttpClient client = new OkHttpClient();
    private static MagentoApi instance;
    private final Properties props;
    private String token;

    public MagentoApi() throws IOException {
        props = new Properties();
        InputStream propFile = getClass().getClassLoader().getResourceAsStream("local.properties");
        if (propFile == null) {
            throw new FileNotFoundException("local.properties file not found in the classpath");
        }
        props.load(propFile);
        propFile.close();
    }

    public static synchronized MagentoApi getInstance() throws IOException {
        if (instance == null) {
            instance = new MagentoApi();
        }
        return instance;
    }

    private void saveTokenToFile(String token) throws IOException {
        File file = new File("token.txt");
        if (!file.exists()) {
            file.createNewFile();
        }
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(token);
        }
    }

    private String loadTokenFromFile() throws IOException {
        File file = new File("token.txt");
        if (!file.exists()) {
            return null;
        }
        try (Scanner scanner = new Scanner(file)) {
            return scanner.hasNext() ? scanner.next() : null;
        }
    }

    public String getCustomerInfo() throws IOException {
        token = loadTokenFromFile();
        if (token == null || token.isEmpty()) {
            token = getNewToken();
        }

        Request request = new Request.Builder()
                .url(BASE_URL + CUSTOMER_ENDPOINT)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer " + token.replace("\"", ""))
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                if (response.code() == 401) {
                    token = getNewToken();
                    return getCustomerInfo();
                }
                throw new IOException("Unexpected code " + response);
            }
            assert response.body() != null;
            return response.body().string();
        }
    }

    private String getNewToken() throws IOException {
        String username = props.getProperty("username");
        String password = props.getProperty("password");
        String json = "{\"username\": \"" + username + "\", \"password\": \"" + password + "\"}";
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .url(BASE_URL + TOKEN_ENDPOINT)
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            if (response.body() == null) {
                throw new IOException("Response body is null");
            }
            token = response.body().string();
            saveTokenToFile(token);
            return token;
        }
    }
}
