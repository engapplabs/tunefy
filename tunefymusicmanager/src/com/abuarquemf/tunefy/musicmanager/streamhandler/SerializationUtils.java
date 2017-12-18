package com.abuarquemf.tunefy.musicmanager.streamhandler;

import java.io.*;
import java.util.Base64;

/**
 * Utils for (de)serialization into Base64-encoded string for future persisting.
 *
 * @author andy (https://gist.github.com/andy722/1524968)
 */
public class SerializationUtils {

    public static <T extends Serializable> String serialize(T item) {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final ObjectOutputStream objectOutputStream;
        try {
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(item);
            objectOutputStream.close();
            return Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray());
        } catch (IOException e) {
            throw new Error(e);
        }
    }

    public static <T extends Serializable> T deserialize(String data) {
        try {
            byte[] dataBytes = Base64.getDecoder().decode(data);
            final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(dataBytes);
            final ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);

            @SuppressWarnings({"unchecked"})
            final T obj = (T) objectInputStream.readObject();

            objectInputStream.close();
            return obj;
        } catch (IOException e) {
            throw new Error(e);
        } catch (ClassNotFoundException e) {
            throw new Error(e);
        }
    }
}
