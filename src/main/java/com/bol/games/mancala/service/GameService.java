package com.bol.games.mancala.service;

import com.bol.games.mancala.dto.GameDto;


public interface GameService {

    GameDto createGame(
            String gameSession
    );

    GameDto move(
            String gameSession, int playerId, int index);

    GameDto undoLastMovement(
            String gameSession);

}
