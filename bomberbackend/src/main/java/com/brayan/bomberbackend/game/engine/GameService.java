package com.brayan.bomberbackend.game.engine;

import com.brayan.bomberbackend.game.model.Bomb;
import com.brayan.bomberbackend.game.model.GameState;
import com.brayan.bomberbackend.game.model.Player;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class GameService {

    private final GameState gameState;

    public GameService() {
        this.gameState = new GameState();
        this.gameState.setPlayers(new ArrayList<>());
        this.gameState.setBombs(new ArrayList<>());
        this.gameState.setMap(new int[11][13]);
    }

    public GameState getGameState() {
        return gameState;
    }

    public Player addPlayer(String username) {
        Player newPlayer = new Player();
        newPlayer.setId(UUID.randomUUID().toString());
        newPlayer.setUsername(username);
        newPlayer.setX(1.0); // Esquina superior izq
        newPlayer.setY(1.0);
        newPlayer.setAlive(true);
        newPlayer.setDirection("DOWN");

        gameState.getPlayers().add(newPlayer);
        return newPlayer;
    }

    public void placeBomb(String playerId) {
        Player player = gameState.getPlayers().stream()
                .filter(p -> p.getId().equals(playerId))
                .findFirst()
                .orElse(null);

        if (player == null || !player.isAlive()) return;


        Bomb bomb = new Bomb();
        bomb.setId(UUID.randomUUID().toString());
        bomb.setOwnerId(playerId);

        bomb.setX((int) Math.round(player.getX()));
        bomb.setY((int) Math.round(player.getY()));

        bomb.setPlantedAt(System.currentTimeMillis());
        bomb.setFuseTime(3000); // 3 segundos para explotar
        bomb.setExplosionRange(2); // Radio de explosión

        gameState.getBombs().add(bomb);
        System.out.println("BOMBA COLOCADA por " + player.getUsername());
    }
}