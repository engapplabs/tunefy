package com.abuarquemf.tunefymusicapi.respositories

import com.abuarquemf.tunefymusicapi.models.Music
import org.springframework.data.mongodb.repository.MongoRepository

interface MusicRepository : MongoRepository<Music, Long> {

    fun findByBand(band: String): List<Music>

    fun findByName(name: String) : Music

}
