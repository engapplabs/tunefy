package com.abuarquemf.tunefy.musicmanager.streamhandler;

import org.apache.commons.codec.binary.Base64;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by animal505 on 09/10/17.
 */
public class StreamsHandler {

    public StreamsHandler() {}

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

    public String codeResource(byte[] imageByteArray) {
        return Base64.encodeBase64URLSafeString(imageByteArray);
    }

    public byte[] decodeResource(String imageDataString) {
        return Base64.decodeBase64(imageDataString);
    }

}
