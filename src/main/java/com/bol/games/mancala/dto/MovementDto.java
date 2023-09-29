package com.bol.games.mancala.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MovementDto {
    String gameSession;
    int playerId;
    int index;

}
