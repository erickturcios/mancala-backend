package com.bol.games.mancala.service;

import com.bol.games.mancala.dto.ConfigurationDto;
import com.bol.games.mancala.dto.GameDto;
import com.bol.games.mancala.helper.ConfigurationHelper;
import com.bol.games.mancala.helper.GameHelper;
import com.bol.games.mancala.jpa.Game;
import com.bol.games.mancala.repository.GameRepository;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class GameService {

    private final ConfigurationService configurationService;
    private final GameRepository gameRepository;

    public GameService(
            ConfigurationService configurationService,
            GameRepository gameRepository
    ){
        this.configurationService = configurationService;
        this.gameRepository = gameRepository;
    }

    /**
     * Get current configuration of create a new session and configuration
     * @param gameSession sessionId
     * @return
     */
    private ConfigurationDto getCurrentConfiguration(String gameSession){
        return ConfigurationHelper.toDto(
                this.configurationService.getOrCreate(gameSession)
        );
    }

    /**
     * Create a new game, initializin pits and total points
     * @param gameSession sessionId
     * @return DTO to be handled by the UI
     */
    public GameDto createGame(
            String gameSession
    ){
        var board = new GameDto();
        var configuration = this.getCurrentConfiguration(gameSession);
        int stones = configuration.getNumberOfStones();

        //Look for past records
        var game = this.gameRepository.findByGameSession(gameSession);
        if(game == null){
            game = new Game();
        }

        //same session as the returned by configuration service
        game.setGameSession(configuration.getGameSession());

        //Initialize game values for each player
        game.setCreatedOn(new Date());
        game.setPlayer01_index01(stones);
        game.setPlayer01_index02(stones);
        game.setPlayer01_index03(stones);
        game.setPlayer01_index04(stones);
        game.setPlayer01_index05(stones);
        game.setPlayer01_index06(stones);
        game.setPlayer01_total(0);
        game.setPlayer02_index01(stones);
        game.setPlayer02_index02(stones);
        game.setPlayer02_index03(stones);
        game.setPlayer02_index04(stones);
        game.setPlayer02_index05(stones);
        game.setPlayer02_index06(stones);
        game.setPlayer02_total(0);

        board = GameHelper.toDto(this.gameRepository.save(game));
        board.setConfiguration(configuration);

        return board;
    }
}
