package com.brayan.bomberbackend.game.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class Player {
    private String id;
    private double x;
    private double y;
    private String direction;
    private boolean isAlive;
}