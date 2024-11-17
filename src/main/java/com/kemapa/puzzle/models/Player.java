package com.kemapa.puzzle.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity // Indica que esta clase es una entidad JPA
public class Player {

    @Id // Indica que este campo es la clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Generación automática de ID
    private Long id; // Agrega un campo id

    private String name; // Nombre del jugador
    private int tamanio; // Tamaño del rompecabezas
    private int bestTime;

    // Constructor
    public Player() {}

    public int getBestTime() {
        return bestTime;
    }

    public void setBestTime(int bestTime) {
        this.bestTime = bestTime;
    }

    // Getters y Setters
    public Long getId() { // Agrega este método
        return id;
    }

    public void setId(Long id) { // Agrega este método
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTamanio() {
        return tamanio;
    }

    public void setTamanio(int tamanio) {
        this.tamanio = tamanio;
    }
}
