package com.abuarquemf.tunefy.musicmanager.models

data class Music(val name: String, val band: String,
                 val musicResource: String) {

    constructor() : this("music_name", "band_name", "music_resource")
}