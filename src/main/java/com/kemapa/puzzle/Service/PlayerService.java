package com.kemapa.puzzle.Service;

import com.kemapa.puzzle.Repository.PlayerRepository;
import com.kemapa.puzzle.models.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    public List<Player> findAll() {
        return playerRepository.findAll();
    }

    public void save(Player player) {
        // Asumir que el mejor tiempo inicial es un número alto, como Integer.MAX_VALUE
        player.setBestTime(Integer.MAX_VALUE);
        playerRepository.save(player);
    }

    public Player findById(Long id) {
        return playerRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        playerRepository.deleteById(id);
    }

    public Player findByName(String name) {
        return playerRepository.findByName(name);
    }

    public void update(Player player) {
        playerRepository.save(player);
    }

}
