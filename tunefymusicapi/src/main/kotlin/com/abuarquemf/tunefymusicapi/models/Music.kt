package com.abuarquemf.tunefymusicapi.models

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "music")
data class Music(val name: String, val band: String,
                 val musicResource: String, @Id var id: Long) {

    constructor() : this("music_name", "band_name", String(), -505)

    constructor(name: String, band: String, musicResource: String) : this(name, band, musicResource, -505)

    constructor(id: Long): this("music_name", "band_name", String(), id)
}