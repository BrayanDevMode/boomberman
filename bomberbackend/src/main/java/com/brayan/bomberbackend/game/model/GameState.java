package com.brayan.bomberbackend.game.model;
import lombok.Data;
import java.util.List;

// Getters and Setters
@Data
public class GameState {
    private int score;
    private int timeleft;
    private int level;

    private int[][] map;
    private List<Player> players;
}