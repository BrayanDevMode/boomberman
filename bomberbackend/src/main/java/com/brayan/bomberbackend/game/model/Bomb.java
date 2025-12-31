package com.brayan.bomberbackend.game.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class Bomb {
    private String id;
    private String ownerId;

    private int x;
    private int y;

    private long plantedAt;
    private int fuseTime;

    private int explosionRange;
    private boolean canPierce;
    private boolean isRemote;

    public boolean shouldExplode(long currentTime) {
        return !isRemote && (currentTime - plantedAt >= fuseTime);
    }

}
