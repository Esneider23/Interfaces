package com.kemapa.puzzle.controllers;

import com.kemapa.puzzle.Service.PlayerService;
import com.kemapa.puzzle.Service.RatingService;
import com.kemapa.puzzle.models.Player;
import com.kemapa.puzzle.models.Rating;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.List;
import java.util.Random;

@Controller
public class GameController {

    @Autowired
    private PlayerService playerService;

    @Autowired
    private RatingService ratingService;

    private static final String IMAGES_PATH = "src/main/resources/static/images/";

    @GetMapping("/")
    public String inicio(HttpSession session, Model model) {
        Player sessionPlayer = (Player) session.getAttribute("player");

        if (sessionPlayer != null) {
            model.addAttribute("player", sessionPlayer);
            List<Rating> historial = ratingService.findAllRatingsByPlayer(sessionPlayer.getId());
            model.addAttribute("historial", historial);
        } else {
            model.addAttribute("player", new Player());
        }

        model.addAttribute("ratings", ratingService.findBestTimesForAllPlayers());
        return "inicio";
    }


    @PostMapping("/comenzar")
    public String comenzarJuego(@ModelAttribute Player player, @RequestParam("tamanio") int tamanio, Model model, HttpSession session) {
        // Verifica si el jugador ya existe en la base de datos
        Player existingPlayer = playerService.findByName(player.getName());
        if (existingPlayer != null) {
            // Si el jugador existe, guarda su información en la sesión
            session.setAttribute("player", existingPlayer);
        } else {
            // Si no hay jugador en la sesión, guarda el nuevo jugador
            playerService.save(player);
            session.setAttribute("player", player);
        }

        String imagenAleatoria = seleccionarImagenAleatoria();
        model.addAttribute("tamanio", tamanio);
        model.addAttribute("imagen", "/images/" + imagenAleatoria);
        model.addAttribute("player", session.getAttribute("player"));
        return "juego";
    }

    private String seleccionarImagenAleatoria() {
        File dir = new File(IMAGES_PATH);
        String[] images = dir.list((d, name) -> name.endsWith(".jpg") || name.endsWith(".png"));
        Random random = new Random();
        int index = random.nextInt(images.length);
        return images[index];
    }

    @PostMapping("/guardarRating")
    public String guardarRating(@RequestParam("time") long time, HttpSession session) {
        Player player = (Player) session.getAttribute("player");
        if (player != null) {

            if (time < player.getBestTime()) {
                player.setBestTime((int) time);
                playerService.update(player);
            }

            Rating rating = new Rating();
            rating.setPlayer(player);
            rating.setTime(time);
            ratingService.save(rating);
        }
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/rankingPorTamanio")
    @ResponseBody
    public String verRankingPorTamanio(@RequestParam("tamanio") int tamanio) {
        List<Rating> ratings = ratingService.findBestRatingsForEachPlayer(tamanio);

        StringBuilder html = new StringBuilder();
        html.append("<tbody>");
        for (int i = 0; i < ratings.size(); i++) {
            Rating rating = ratings.get(i);
            html.append("<tr>")
                    .append("<td>").append(i + 1).append("</td>") // puesto
                    .append("<td>").append(rating.getPlayer().getName()).append("</td>")
                    .append("<td class='time'>").append(rating.getTime()).append("</td>")
                    .append("</tr>");
        }
        html.append("</tbody>");

        return html.toString();
    }
}
