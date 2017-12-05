package com.abuarquemf.tunefymusicapi.models

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "music")
data class Music(val name: String, val band: String,
                 val musicResource: String, @Id val id: Long) {

    constructor() : this("music_name", "band_name", "music_resource", -505)
}
