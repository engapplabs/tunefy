package com.abuarquemf.tunefy.desktopapp.streamhandler

import java.io.*

/**
 * This object handles common situations using resources
 * streams. Some features:
 *
 * (*) parse stream into byte arrays;
 * (*)
 *
 * Aurelio Buarque (abuarquemf@gmail.com)
 */
object StreamUtils {

    /**
     * It gets a stream path and parses it into a
     * byte array.
     *
     * @param stream path
     * @return stream as byte array
     *
     */
    fun streamToBytes(streamPath: String): ByteArray {
        var inputStream: InputStream? = null
        try {
            inputStream = FileInputStream(streamPath)
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }
        val buffer = ByteArray(8192)
        var bytesRead: Int
        val output = ByteArrayOutputStream()
        try {
            bytesRead = inputStream!!.read(buffer)
            while (bytesRead  != -1) {
                output.write(buffer, 0, bytesRead)
                bytesRead = inputStream!!.read(buffer)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return output.toByteArray()
    }

    /**
     * It gets a stream as a byte array to create
     * a temporary file in memory and then return
     * this object file.
     *
     * You should pass as argument strings to be
     * the extension and prefix file (prefix is
     * not the name, because between prefix and
     * extension will be placed some random values.
     *
     * @param stream as byte array
     * @param string to be the file prefix
     * @param string with file extension
     * @return temporary file
     */
    fun getTempFile(resourceAsBytes: ByteArray,
                    prefix: String, extension: String): File? {
        try {
            val tempFile = File.createTempFile(prefix, extension, null)
            tempFile.deleteOnExit()
            val fos = FileOutputStream(tempFile)
            fos.write(resourceAsBytes)
            fos.close()
            return tempFile
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null
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
    fun createResourceFile(resourceAsBytes: ByteArray, resourceName: String) {
        try {
            FileOutputStream(resourceName).use { fos ->
                fos.write(resourceAsBytes)
                fos.close()
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private val MAXLENGTH = 100000000

    @Throws(IOException::class)
    fun getBytesFromFile(file: File): ByteArray {
        var `is`: InputStream? = null
        val ret: ByteArray
        try {
            val length = file.length()
            if (length == MAXLENGTH.toLong())
                throw IllegalArgumentException("File is too big")
            ret = ByteArray(length.toInt())
            `is` = FileInputStream(file)
            `is`.read(ret)
        } finally {
            if (`is` != null) try {
                `is`.close()
            } catch (ex: IOException) {
            }
        }
        return ret
    }
}