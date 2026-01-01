package com.brayan.bomberbackend.game.engine;

import com.brayan.bomberbackend.game.model.Bomb;
import com.brayan.bomberbackend.game.model.GameState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;

@Component
public class GameLoop {

    private final GameService gameService;
    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public GameLoop(GameService gameService, SimpMessagingTemplate messagingTemplate) {
        this.gameService = gameService;
        this.messagingTemplate = messagingTemplate;
    }

    @Scheduled(fixedRate = 50)
    public void gameTick() {
        GameState state = gameService.getGameState();
        long now = System.currentTimeMillis();

        List<Bomb> bombs = state.getBombs();

        Iterator<Bomb> iterator = bombs.iterator();
        while (iterator.hasNext()) {
            Bomb bomb = iterator.next();

            if (bomb.shouldExplode(now)) {
                System.out.println("EXPLOSIÓN en " + bomb.getX() + "," + bomb.getY());

                iterator.remove();
            }
        }

        messagingTemplate.convertAndSend("/topic/game-state", state);
    }
}