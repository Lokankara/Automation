package com.luma.api.services;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;

public class MagentoApiService {
    public static final String BASE_URL_REST = "https://magento.softwaretestingboard.com/rest/default/V1/";
    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    private static final String TOKEN_ENDPOINT = "integration/customer/token/";
    private static final String CUSTOMER_ENDPOINT = "customers/me";
    private final OkHttpClient client = new OkHttpClient();
    private static MagentoApiService instance;
    private final Properties props;
    private static final String token = "cdxf96vcah8pbivc3zzrshd8t15ikua5";

    public MagentoApiService() throws IOException {
        props = new Properties();
        InputStream propFile = getClass().getClassLoader().getResourceAsStream("local.properties");
        if (propFile == null) {
            throw new FileNotFoundException("local.properties file not found in the classpath");
        }
        props.load(propFile);
        propFile.close();
    }

    public static synchronized MagentoApiService getInstance() throws IOException {
        if (instance == null) {
            instance = new MagentoApiService();
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
//        token = loadTokenFromFile();
//        if (token == null || token.isEmpty()) {
//            token = getNewToken();
//        }

        Request request = new Request.Builder()
                .url(BASE_URL_REST + CUSTOMER_ENDPOINT)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer " + token)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    private String getNewToken() throws IOException {
        String username = props.getProperty("username");
        String password = props.getProperty("password");
        String json = "{\"username\": \"" + username + "\", \"password\": \"" + password + "\"}";
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .url(BASE_URL_REST + TOKEN_ENDPOINT)
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            if (response.body() == null) {
                throw new IOException("Response body is null");
            }
//            token = response.body().string();
            saveTokenToFile(token);
            return token;
        }
    }
}
