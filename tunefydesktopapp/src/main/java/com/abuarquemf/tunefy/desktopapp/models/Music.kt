package com.abuarquemf.tunefy.desktopapp.models

data class Music(val name: String, val band: String,
                 val musicResource: String,val imageResource: String, val id: Long) {

    constructor() : this("music_name", "band_name", String(),String(), -505)

    constructor(name: String, band: String, musicResource: String, imageResource: String) : this(name, band, musicResource, imageResource,-505)

    constructor(id: Long): this("music_name", "band_name", String(), String(), id)
}
