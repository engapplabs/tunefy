package com.abuarquemf.tunefy.musicmanager.connectionhandler;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class RestHandler {

    private static RestHandler INSTANCE = null;

    private URL url;
    private HttpURLConnection connection;

    private int responseCode;

    private BufferedReader in;

    private StringBuffer responseBuffer;

    private RestHandler() {}

    public static RestHandler getInstance() {
        if(INSTANCE == null)
            INSTANCE = new RestHandler();
        return INSTANCE;
    }

    /**
     * It gets the url to make a get and returns
     * request response.
     *
     * @param urlGet
     * @return request response
     */
    public String doGet(String urlGet) {
        String requestResponse = null;
        try {
            url = new URL(urlGet);
            connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");

            responseCode = connection.getResponseCode();
            System.out.println("\nSending 'GET' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);

            in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            String inputLine;
            responseBuffer = new StringBuffer();

            while ((inputLine = in.readLine()) != null)
                responseBuffer.append(inputLine);
            in.close();

            requestResponse = responseBuffer.toString();
            System.out.println(requestResponse);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return requestResponse;
    }

    /**
     * It gets an url to do a post and the
     * object that goes on request body.
     *
     * @param urlPost
     * @param object
     * @return request response
     */
    public String doPost(String urlPost, Object object) {
        String requestReponse = null;
        try {
            url = new URL(urlPost);
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput (true);
            connection.setDoOutput (true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.connect();

            String content = (new Gson()).toJson(object);

            DataOutputStream output = new DataOutputStream(connection.getOutputStream());

            output.writeBytes(content);
            output.flush();
            output.close();

            responseCode = connection.getResponseCode();
            System.out.println("\nSending 'POST' request to URL : " + url);
            System.out.println("POST params: " + content);
            System.out.println("Response Code : " + responseCode);

            in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            String inputLine;
            responseBuffer = new StringBuffer();

            while ((inputLine = in.readLine()) != null)
                responseBuffer.append(inputLine);
            in.close();

            System.out.println(responseBuffer.toString());
            requestReponse = responseBuffer.toString();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return requestReponse;
    }

    /**
     * It gets an url to do a post and the
     * object that goes on request body.
     *
     * @param urlDelete
     * @param object
     * @return request response
     */
    public String doDelete(String urlDelete, Object object) {
        String requestReponse = null;
        try {
            url = new URL(urlDelete);
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput (true);
            connection.setDoOutput (true);
            connection.setRequestMethod("DELETE");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.connect();

            String content = (new Gson()).toJson(object);

            DataOutputStream output = new DataOutputStream(connection.getOutputStream());

            output.writeBytes(content);
            output.flush();
            output.close();

            responseCode = connection.getResponseCode();
            System.out.println("\nSending 'DELETE' request to URL : " + url);
            System.out.println("DELETE params: " + content);
            System.out.println("Response Code : " + responseCode);

            in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            String inputLine;
            responseBuffer = new StringBuffer();

            while ((inputLine = in.readLine()) != null)
                responseBuffer.append(inputLine);
            in.close();

            System.out.println(responseBuffer.toString());
            requestReponse = responseBuffer.toString();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return requestReponse;
    }

    /**
     * It gets an url to do a post and the
     * object that goes on request body.
     *
     * @param urlPut
     * @param object
     * @return request response
     */
    public String doPut(String urlPut, Object object) {
        String requestReponse = null;
        try {
            url = new URL(urlPut);
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput (true);
            connection.setDoOutput (true);
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.connect();

            String content = (new Gson()).toJson(object);

            DataOutputStream output = new DataOutputStream(connection.getOutputStream());

            output.writeBytes(content);
            output.flush();
            output.close();

            responseCode = connection.getResponseCode();
            System.out.println("\nSending 'PUT' request to URL : " + url);
            System.out.println("PUT params: " + content);
            System.out.println("Response Code : " + responseCode);

            in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            String inputLine;
            responseBuffer = new StringBuffer();

            while ((inputLine = in.readLine()) != null)
                responseBuffer.append(inputLine);
            in.close();

            System.out.println(responseBuffer.toString());
            requestReponse = responseBuffer.toString();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return requestReponse;
    }
}
