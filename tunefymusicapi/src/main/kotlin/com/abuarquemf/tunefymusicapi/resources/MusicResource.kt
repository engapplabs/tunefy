package com.abuarquemf.tunefymusicapi.resources

import com.abuarquemf.tunefymusicapi.models.Music
import com.abuarquemf.tunefymusicapi.respositories.MusicRepository
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping(value = "/tunefymusicapi")
class MusicResource(val musicRepository: MusicRepository) {



    @GetMapping
    @RequestMapping(value = "/band/{band}")
    fun getByBand(@PathVariable band: String) = musicRepository.findByBand(band)

    /**
     *
     * Music object that comes inside post has no
     * valid ID, it has only name, band name and audio
     * resources. Id will be defined here.
     *
     * @param music object without id
     */
    @PostMapping
    @RequestMapping(value = "/add")
    fun addMusic(@RequestBody music: Music): Music {
        //this music object id is define using system time
        val validMusic = Music(music.name, music.band,
                music.musicResource, Date().getTime() / 1000)
        musicRepository.save(validMusic)
        return validMusic
    }
}
