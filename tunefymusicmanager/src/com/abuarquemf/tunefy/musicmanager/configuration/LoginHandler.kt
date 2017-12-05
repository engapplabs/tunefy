package com.abuarquemf.tunefy.musicmanager.configuration

import java.io.File

object LoginHandler {

    private var configFile: File? = null

    init {
        this.configFile = File("src/com/abuarquemf/tunefy/musicmanager/configuration/tunefy.config")
    }

    fun validateLogin(givenLogin: String, givenPassword: String): Boolean {
        val parameters: List<String> = configFile!!.bufferedReader().readLines()
        val properLogin = parameters[0].replace("LOGIN=", "")
        val properPassword = parameters[1].replace("PASS=", "")
        return givenLogin.equals(properLogin) && givenPassword.equals(properPassword)
    }
}

