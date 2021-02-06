package com.galvanize.muzicshare.repository;

import com.galvanize.muzicshare.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {

    @Query("SELECT s FROM Song s where s.name = ?1")
    Optional<Song> findByName(String name);
}
