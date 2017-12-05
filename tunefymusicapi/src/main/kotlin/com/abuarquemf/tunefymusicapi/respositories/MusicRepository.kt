package com.abuarquemf.tunefymusicapi.respositories

import com.abuarquemf.tunefymusicapi.models.Music
import org.springframework.data.mongodb.repository.MongoRepository

interface MusicRepository : MongoRepository<Music, Long>
