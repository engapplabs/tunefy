package com.abuarquemf.tunefy.musicmanager.models

data class Music(val name: String, val band: String,
                 val musicResource: String, val id: Long) {

    constructor() : this("music_name", "band_name", "music_resource", -505)

    constructor(name: String, band: String, musicResource: String) : this(name, band, musicResource, -505)
}