package com.abuarquemf.tunefy.desktopapp.streamhandler

import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.nio.file.Files

class TunesResourcesHandler {

    /**
     * It gets tune resources as a string and
     * as it was serialized as a File object,
     * it'd deserialize same object type.
     *
     * @param tune resource
     */
    fun saveTune(tuneResource: String, tunePath: String) {
        val tuneFile = SerializationUtils.deserialize<File>(tuneResource)
        createResourceFile(Files.readAllBytes(tuneFile.toPath()), tuneResource)
    }

    /**
     * It gets a byte array and a path to create
     * a file and save tune on memory.
     *
     * @param tune as byte array
     * @param new tune path in memory
     *
     */
    private fun createResourceFile(resourceAsBytes: ByteArray, resourceName: String) {
        try {
            FileOutputStream(resourceName).use { fos ->
                fos.write(resourceAsBytes)
                fos.close()
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}
