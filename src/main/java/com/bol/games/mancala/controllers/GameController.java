package com.bol.games.mancala.controllers;

import com.bol.games.mancala.dto.GameDto;
import com.bol.games.mancala.dto.Movement;
import com.bol.games.mancala.service.GameService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/game")
@CrossOrigin
public class GameController {

    private final GameService gameService;

    public GameController(
            GameService gameService
    ){
        this.gameService = gameService;
    }

    @PostMapping("/{gameSession}")
    public GameDto createGame(
            @PathVariable("gameSession") String gameSession
    ){
        return this.gameService.createGame(gameSession);
    }

    @PutMapping
    public GameDto move(@RequestBody Movement movement
    ){
        return this.gameService.move(
                movement.getGameSession(),
                movement.getPlayerId(),
                movement.getIndex()
        );
    }

    @PutMapping("/undoMovement/{gameSession}")
    public GameDto undoLastMovement(
            @PathVariable("gameSession") String gameSession
    ){
        return this.gameService.undoLastMovement(gameSession);
    }
}
