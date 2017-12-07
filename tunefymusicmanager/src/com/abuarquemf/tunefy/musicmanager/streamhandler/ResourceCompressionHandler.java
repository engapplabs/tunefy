package com.abuarquemf.tunefy.musicmanager.streamhandler;

import org.apache.commons.codec.binary.Base64;

import java.io.*;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;

/**
 * This class is useful to send resources across
 * the web as byte arrays parsed into strings.
 *
 * It gets resource path an builds a string encoded
 * with Apache Commons Base64 and then compress this
 * string to return.
 *
 * To make inverse process just pass resource as
 * a string encoded to get the resource as a byte array.
 *
 * User can also save the file as byte array in memory.
 *
 * Aurelio Buarque (abuarquemf@gmail.com)
 *
 */
public class ResourceCompressionHandler {

    private enum StringCompressor {;
        public static byte[] compress(String text) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            try {
                OutputStream out = new DeflaterOutputStream(baos);
                out.write(text.getBytes("UTF-8"));
                out.close();
            } catch (IOException e) {
                throw new AssertionError(e);
            }
            return baos.toByteArray();
        }

        public static String decompress(byte[] bytes) {
            InputStream in = new InflaterInputStream(new ByteArrayInputStream(bytes));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            try {
                byte[] buffer = new byte[8192];
                int len;
                while((len = in.read(buffer))>0)
                    baos.write(buffer, 0, len);
                return new String(baos.toByteArray(), "UTF-8");
            } catch (IOException e) {
                throw new AssertionError(e);
            }
        }
    }

    /**
     * It gets resource path to create string
     * the compressed string encoded.
     *
     * @param resourcePath
     * @return resource compressed
     */
    public String compressResource(String resourcePath) {
        try {
            File file = new File(resourcePath);
            InputStream fileInputStream = new FileInputStream(file);

            byte[] resourceBytes = new byte[(int)file.length()];

            fileInputStream.read(resourceBytes, 0, resourceBytes.length);
            fileInputStream.close();

            String resourceAsString = Base64.encodeBase64String(resourceBytes);

            byte[] compress = StringCompressor.compress(resourceAsString);

            return StringCompressor.decompress(compress);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Get resource as a string and returns it as
     * a byte array.
     *
     * @param compressedResource
     * @return resource as byte array
     */
    public byte[] decompressResource(String compressedResource) {
        try {
            return Base64.decodeBase64(compressedResource);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return null;
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
}
