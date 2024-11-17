-- database: :memory:

CREATE DATABASE IF NOT EXISTS puzzle;

-- Usar la base de datos
USE puzzle;

CREATE TABLE if not exists player
(
    id        BIGINT AUTO_INCREMENT NOT NULL,
    name      VARCHAR(255)          NULL,
    tamanio   INT                   NOT NULL,
    best_time INT                   NOT NULL,
    CONSTRAINT pk_player PRIMARY KEY (id)
);
CREATE TABLE if not exists rating
(
    id        BIGINT AUTO_INCREMENT NOT NULL,
    player_id BIGINT                NULL,
    time      BIGINT                NOT NULL,
    CONSTRAINT pk_rating PRIMARY KEY (id)
);

ALTER TABLE rating
    ADD CONSTRAINT FK_RATING_ON_PLAYER FOREIGN KEY (player_id) REFERENCES player (id);