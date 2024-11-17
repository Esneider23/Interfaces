package com.kemapa.puzzle.Service;

import com.kemapa.puzzle.Repository.RatingRepository;
import com.kemapa.puzzle.models.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    public List<Rating> findAll() {
        return ratingRepository.findAll();
    }

    public Rating save(Rating rating) {
        return ratingRepository.save(rating);
    }

    public Rating findById(Integer id) {
        return ratingRepository.findById(id).orElse(null);
    }

    public void deleteById(Integer id) {
        ratingRepository.deleteById(id);
    }

    public List<Rating> findBestTimesForAllPlayers() {
        return ratingRepository.findBestTimesForEachPlayerOrderByTime();
    }

    public List<Rating> findBestRatingsForEachPlayer(int tamanio) {
        return ratingRepository.findBestTimesForEachPlayerByTamanio(tamanio);
    }

    public List<Rating> findAllRatingsByPlayer (Long id) {
        return ratingRepository.findAllRatingsByPlayer(id);
    }
}
