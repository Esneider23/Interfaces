package com.kemapa.puzzle.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
public class ErrorController {

    @GetMapping
    public String handleError() {
        // Redirige a la página de inicio en caso de un error 404
        return "redirect:/juego/inicio";
    }
}
