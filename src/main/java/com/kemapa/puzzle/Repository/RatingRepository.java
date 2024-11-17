package com.kemapa.puzzle.Repository;

import com.kemapa.puzzle.models.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Integer> {
    @Query("SELECT r FROM Rating r WHERE r.time = (SELECT MIN(r2.time) FROM Rating r2 WHERE r2.player = r.player) ORDER BY r.time ASC")
    List<Rating> findBestTimesForEachPlayerOrderByTime();

    @Query("SELECT r FROM Rating r WHERE r.time = (SELECT MIN(r2.time) FROM Rating r2 WHERE r2.player = r.player AND r2.player.tamanio = :tamanio) AND r.player.tamanio = :tamanio ORDER BY r.time ASC")
    List<Rating> findBestTimesForEachPlayerByTamanio(@Param("tamanio") int tamanio);

    @Query("SELECT r FROM Rating r WHERE r.player.id = :playerId ORDER BY r.time ASC")
    List<Rating> findAllRatingsByPlayer(@Param("playerId") Long playerId);

}
