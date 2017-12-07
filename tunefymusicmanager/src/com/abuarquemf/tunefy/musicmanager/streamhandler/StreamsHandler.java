package com.abuarquemf.tunefy.musicmanager.streamhandler;

import org.apache.commons.codec.binary.Base64;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * This abstract class is useful to get
 * an resource path and parse it into a
 * coded string using Apache commons Base64
 * and to create a file from a resource encoded.
 */
import org.apache.commons.codec.binary.Base64;

import java.io.*;

public class StreamsHandler {

    /**
     * It gets a resource (File, Image, text...)
     * as an InputStream and parses it into a byte array.
     *
     * @param stream
     * @return array byte
     */
    public byte[] streamToBytes(InputStream stream) {
        try {
            byte[] bytes = new byte[1024];
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int readBytes;
            while((readBytes = stream.read(bytes)) > 0)
                baos.write(bytes, 0, readBytes);
            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * It gets resource path an parses it into
     * a byte array.
     *
     * @param resourcePath
     * @return resource byte array
     */
    public byte[] stringPathToBytes(String resourcePath) {
        try {
            return streamToBytes(new FileInputStream(resourcePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * It gets a resource as string( its path) and
     * parses it into a Apache Commons Base64 string.
     *
     * @param resourcePath
     * @return Base64 encoded string
     */
    public String stringToBase64(String resourcePath) {
        try {
            byte[]musicAsByteArray = streamToBytes(
                    new FileInputStream(new File(resourcePath)));
            return codeResource(musicAsByteArray);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Gets a byte array, encodes it into a Base64
     * String.
     *
     * @param resource as byte array
     * @return resource encoded
     */
    public String codeResource(byte[] sourceByteArray) {
        return Base64.encodeBase64URLSafeString(sourceByteArray);
    }

    /**
     * Gets a resource encoded as a string with Base64
     * and parses into a byte array.
     *
     * @param resource encoded
     * @return resource as byte array
     */
    public byte[] decodeResource(String sourceDataString) {
        return Base64.decodeBase64(sourceDataString);
    }

    /**
     * It gets a resource as a byte array
     * and the name (path) that this resource
     * should have when saved and save
     * it in memory.
     *
     * @param resource as byte array
     * @param name to save resource
     */
    public void createResourceFile(byte[] resourceAsBytes, String resourceName) {
        try (FileOutputStream fos = new FileOutputStream(resourceName)) {
            fos.write(resourceAsBytes);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * It gets a resource as a String coded with apache
     * commons Base64 and the name (path) that this resource
     * should have when saved.
     *
     * @param resource as String
     * @param name to save resource
     */
    public void createResourceFile(String resourceAsString, String resourceName) {
        try (FileOutputStream fos = new FileOutputStream(resourceName)) {
            fos.write(decodeResource(resourceAsString));
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
