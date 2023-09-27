package com.bol.games.mancala.service;

import com.bol.games.mancala.dto.ConfigurationDto;
import com.bol.games.mancala.dto.GameDto;
import com.bol.games.mancala.helper.ConfigurationHelper;
import com.bol.games.mancala.helper.GameHelper;
import com.bol.games.mancala.jpa.Game;
import com.bol.games.mancala.repository.GameRepository;
import org.springframework.stereotype.Service;


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
     * @return configuration DTO
     */
    private ConfigurationDto getCurrentConfiguration(String gameSession){
        return ConfigurationHelper.toDto(
                this.configurationService.getOrCreate(gameSession)
        );
    }

    /**
     * Create a new game, initialize pits and total points
     * @param gameSession sessionId
     * @return DTO to be handled by the UI
     */
    public GameDto createGame(
            String gameSession
    ){
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
        game.setPlayerToMoveNext(1); //by default player 1 start playing
        game.setWinner(0); //no winner by default

        var board = GameHelper.toDto(this.gameRepository.save(game));
        board.setConfiguration(configuration);

        return board;
    }


    /**
     * Apply a movement based on current game session, player ID and current pit (index)
     * @param gameSession identifier of current session
     * @param playerId player id: 1 or 2
     * @param index value between 0 and 6
     * @return Game DTO
     */
    public GameDto move(
            String gameSession, int playerId, int index){
        var game = this.gameRepository.findByGameSession(gameSession);

        //if game is nto found a new game is created
        if(game == null){
            return this.createGame(gameSession);
        }
        var configuration = this.getCurrentConfiguration(gameSession);

        //wrong turn makes no effect
        //wring index makes no effect
        if(playerId != game.getPlayerToMoveNext()
            || index < 0 || index > 5
        ){
            var board = GameHelper.toDto(game);
            board.setConfiguration(configuration);
            return board;
        }

        var boardArray = GameHelper.convertGameToIntArray(game);
        var currentIndex = (playerId ==1) ? index : index +7;
        var value = boardArray[currentIndex];
        boardArray[currentIndex] = 0;
        //No stones are put in the opponents' big pit
        var excludedIndex = (playerId ==1) ? 13 : 6;

        while(value > 0){
            currentIndex++;
            if(currentIndex > 13){
                currentIndex = 0;
            }
            if(currentIndex == excludedIndex){
                continue;
            }

            boardArray[currentIndex]++;
            value--;

            //last stone lands in empty pit
            if(value == 0 && boardArray[currentIndex] == 1){
                GameHelper.evaluateEmptyPit(boardArray, playerId, currentIndex);
            }
        }

        //verify if a there's a winner
        game.setWinner(GameHelper.evaluateGameOver(boardArray));

        //update Entity with modified values after movement
        GameHelper.convertIntArrayToGame(game, boardArray);

        if(game.getWinner() == 0){
            //If the player's last stone lands in his own big pit, he gets another turn
            boolean oneMoreTurn = (playerId ==1 && currentIndex == 6)
                    || (playerId == 2 && currentIndex == 13);

            if(!oneMoreTurn) {
                //alternate turns
                game.setPlayerToMoveNext((playerId == 1) ? 2 : 1);
            }
        }else{
            game.setPlayerToMoveNext(0);
        }

        game = this.gameRepository.save(game);

        //prepare returned DTO
        var board = GameHelper.toDto(game);
        board.setConfiguration(configuration);

        return board;
    }

}
