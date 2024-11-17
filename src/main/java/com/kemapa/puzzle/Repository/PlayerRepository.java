package com.kemapa.puzzle.Repository;

import com.kemapa.puzzle.models.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    Player findByName(String name);
}
