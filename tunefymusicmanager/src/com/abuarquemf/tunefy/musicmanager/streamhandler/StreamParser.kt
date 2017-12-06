package com.abuarquemf.tunefy.musicmanager.streamhandler

import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

/**
 * This abstract class is useful to get
 * an resource path and parse it into a
 * coded string using Apache commons Base64
 * and to create a file from a resource encoded.
 */
abstract class StreamParser {

    /**
     * It gets path resource, create a file with
     * it to parse to an byte array and them codes
     * it to an string using apache commons Base64
     *
     * @param path of resource
     * @return resource coded as string
     */
    fun parseResource(resourcePath: String): String {
        val handler = StreamsHandler()
        val musicAsByteArray = handler.streamToBytes(
                FileInputStream(File(resourcePath)))
        return handler.codeResource(musicAsByteArray)
    }

    /**
     * It gets a resource as a String coded with apache
     * commons Base64 and the name that this resource
     * should have when saved.
     *
     * @param resource as String
     * @param name to save resource
     */
    fun createResourceFile(resourceAsString: String, resourceName: String) {
        var fileOuputStream: FileOutputStream? = null
        try {
            val handler = StreamsHandler()
            fileOuputStream = FileOutputStream(resourceName)
            fileOuputStream.write(handler.decodeResource(resourceAsString))
        } finally {
            fileOuputStream!!.close()
        }
    }
}