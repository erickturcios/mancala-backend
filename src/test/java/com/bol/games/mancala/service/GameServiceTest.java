package com.bol.games.mancala.service;

import com.bol.games.mancala.jpa.Configuration;
import com.bol.games.mancala.jpa.Game;
import com.bol.games.mancala.jpa.GameHistory;
import com.bol.games.mancala.jpa.GameSession;
import com.bol.games.mancala.repository.ConfigurationRepository;
import com.bol.games.mancala.repository.GameHistoryRepository;
import com.bol.games.mancala.repository.GameRepository;
import com.bol.games.mancala.repository.GameSessionRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;


@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class GameServiceTest {

    private final static String SESSION = "4a525c64-859d-4c95-99dd-44e8140dc5a1";
    private final static String DUMMY = "dummy";
    private final int NUMBER_OF_STONES = 6;
    @Autowired
    private GameServiceImpl gameService;

    @MockBean
    private ConfigurationRepository configurationRepository;

    @MockBean
    private GameSessionRepository gameSessionRepository;

    @MockBean
    private GameRepository gameRepository;

    @MockBean
    private GameHistoryRepository gameHistoryRepository;

    @BeforeEach
    void setUp(){
        var gameSession = new GameSession();
        gameSession.setId(1L);
        gameSession.setSessionId(SESSION);
        gameSession.setCreatedOn(new Date());

        doReturn(true).when(gameSessionRepository).existsBySessionId(SESSION);

        var config = new Configuration(1L, SESSION,
                6, true, false,
                "Alias: Player 1", "Alias: Player 2",
                new Date(), null);

        doReturn(config).when(configurationRepository).findByGameSession(SESSION);
        doReturn(true).when(gameSessionRepository).existsBySessionId(SESSION);

        doReturn(null).when(configurationRepository).findByGameSession(DUMMY);
        doReturn(false).when(gameSessionRepository).existsBySessionId(DUMMY);
    }


    @Test
    @Order(1)
    void createGame() {
        var newGame = new Game(99L, SESSION, NUMBER_OF_STONES,
                NUMBER_OF_STONES, NUMBER_OF_STONES, NUMBER_OF_STONES,
                NUMBER_OF_STONES, NUMBER_OF_STONES, 0,
                NUMBER_OF_STONES, NUMBER_OF_STONES, NUMBER_OF_STONES,
                NUMBER_OF_STONES, NUMBER_OF_STONES, NUMBER_OF_STONES,
                0, 1, 0,
                new Date(), null);

        doReturn(newGame).when(gameRepository).findByGameSession(SESSION);
        doReturn(newGame).when(gameRepository).save(newGame);

        var gameDto = this.gameService.createGame(SESSION);

        assertEquals(0, gameDto.getTotalPlayer1());
        assertEquals(0, gameDto.getTotalPlayer2());

        assertEquals(NUMBER_OF_STONES, gameDto.getPlayer01()[0]);
        assertEquals(NUMBER_OF_STONES, gameDto.getPlayer01()[1]);
        assertEquals(NUMBER_OF_STONES, gameDto.getPlayer01()[2]);
        assertEquals(NUMBER_OF_STONES, gameDto.getPlayer01()[3]);
        assertEquals(NUMBER_OF_STONES, gameDto.getPlayer01()[4]);
        assertEquals(NUMBER_OF_STONES, gameDto.getPlayer01()[5]);

        assertEquals(NUMBER_OF_STONES, gameDto.getPlayer02()[0]);
        assertEquals(NUMBER_OF_STONES, gameDto.getPlayer02()[1]);
        assertEquals(NUMBER_OF_STONES, gameDto.getPlayer02()[2]);
        assertEquals(NUMBER_OF_STONES, gameDto.getPlayer02()[3]);
        assertEquals(NUMBER_OF_STONES, gameDto.getPlayer02()[4]);
        assertEquals(NUMBER_OF_STONES, gameDto.getPlayer02()[5]);

        assertEquals(0, gameDto.getWinner());
        assertEquals(1, gameDto.getPlayerToMoveNext());
    }

    @Test
    @Order(2)
    void move() {
        var game = new Game(99L, SESSION, NUMBER_OF_STONES,
                NUMBER_OF_STONES, NUMBER_OF_STONES, NUMBER_OF_STONES,
                NUMBER_OF_STONES, NUMBER_OF_STONES, 0,
                NUMBER_OF_STONES, NUMBER_OF_STONES, NUMBER_OF_STONES,
                NUMBER_OF_STONES, NUMBER_OF_STONES, NUMBER_OF_STONES,
                0, 1, 0,
                new Date(), null);

        doReturn(game).when(gameRepository).findByGameSession(SESSION);

        var gameUpdated = new Game(99L, SESSION, 0,
                NUMBER_OF_STONES+1, NUMBER_OF_STONES+1, NUMBER_OF_STONES+1,
                NUMBER_OF_STONES+1, NUMBER_OF_STONES+1, 1,
                NUMBER_OF_STONES, NUMBER_OF_STONES, NUMBER_OF_STONES,
                NUMBER_OF_STONES, NUMBER_OF_STONES, NUMBER_OF_STONES,
                0, 1, 0,
                new Date(), null);

        when(gameRepository.save(isA(Game.class))).thenReturn(gameUpdated);

        var gameDto = this.gameService.move(SESSION, 1, 0);

        assertEquals(1, gameDto.getTotalPlayer1());
        assertEquals(0, gameDto.getTotalPlayer2());

        assertEquals(0, gameDto.getPlayer01()[0]);
        assertEquals(NUMBER_OF_STONES+1, gameDto.getPlayer01()[1]);
        assertEquals(NUMBER_OF_STONES+1, gameDto.getPlayer01()[2]);
        assertEquals(NUMBER_OF_STONES+1, gameDto.getPlayer01()[3]);
        assertEquals(NUMBER_OF_STONES+1, gameDto.getPlayer01()[4]);
        assertEquals(NUMBER_OF_STONES+1, gameDto.getPlayer01()[5]);

        assertEquals(NUMBER_OF_STONES, gameDto.getPlayer02()[0]);
        assertEquals(NUMBER_OF_STONES, gameDto.getPlayer02()[1]);
        assertEquals(NUMBER_OF_STONES, gameDto.getPlayer02()[2]);
        assertEquals(NUMBER_OF_STONES, gameDto.getPlayer02()[3]);
        assertEquals(NUMBER_OF_STONES, gameDto.getPlayer02()[4]);
        assertEquals(NUMBER_OF_STONES, gameDto.getPlayer02()[5]);

        assertEquals(0, gameDto.getWinner());
        assertEquals(1, gameDto.getPlayerToMoveNext());
    }

    @Test
    @Order(3)
    void undoLastMovement() {
        Date date = new Date();

        var gameCurrent = new Game(99L, SESSION, 0,
                NUMBER_OF_STONES+1, NUMBER_OF_STONES+1, NUMBER_OF_STONES+1,
                NUMBER_OF_STONES+1, NUMBER_OF_STONES+1, 1,
                NUMBER_OF_STONES, NUMBER_OF_STONES, NUMBER_OF_STONES,
                NUMBER_OF_STONES, NUMBER_OF_STONES, NUMBER_OF_STONES,
                0, 1, 0,
                date, null);

        doReturn(gameCurrent).when(gameRepository).findByGameSession(SESSION);

        var gameHistory = new GameHistory(0L, SESSION, NUMBER_OF_STONES,
                NUMBER_OF_STONES, NUMBER_OF_STONES, NUMBER_OF_STONES,
                NUMBER_OF_STONES, NUMBER_OF_STONES, 0,
                NUMBER_OF_STONES, NUMBER_OF_STONES, NUMBER_OF_STONES,
                NUMBER_OF_STONES, NUMBER_OF_STONES, NUMBER_OF_STONES,
                0, 1, 0,
                date);
        var game = new Game(99L, SESSION, NUMBER_OF_STONES,
                NUMBER_OF_STONES, NUMBER_OF_STONES, NUMBER_OF_STONES,
                NUMBER_OF_STONES, NUMBER_OF_STONES, 0,
                NUMBER_OF_STONES, NUMBER_OF_STONES, NUMBER_OF_STONES,
                NUMBER_OF_STONES, NUMBER_OF_STONES, NUMBER_OF_STONES,
                0, 1, 0,
                date, null);

        doReturn(gameHistory).when(gameHistoryRepository).findTop1ByGameSessionOrderByCreatedOnDesc(SESSION);

        when(gameRepository.save(isA(Game.class))).thenReturn(game);

        var gameDto = this.gameService.undoLastMovement(SESSION);

        assertEquals(0, gameDto.getTotalPlayer1());
        assertEquals(0, gameDto.getTotalPlayer2());

        assertEquals(NUMBER_OF_STONES, gameDto.getPlayer01()[0]);
        assertEquals(NUMBER_OF_STONES, gameDto.getPlayer01()[1]);
        assertEquals(NUMBER_OF_STONES, gameDto.getPlayer01()[2]);
        assertEquals(NUMBER_OF_STONES, gameDto.getPlayer01()[3]);
        assertEquals(NUMBER_OF_STONES, gameDto.getPlayer01()[4]);
        assertEquals(NUMBER_OF_STONES, gameDto.getPlayer01()[5]);

        assertEquals(NUMBER_OF_STONES, gameDto.getPlayer02()[0]);
        assertEquals(NUMBER_OF_STONES, gameDto.getPlayer02()[1]);
        assertEquals(NUMBER_OF_STONES, gameDto.getPlayer02()[2]);
        assertEquals(NUMBER_OF_STONES, gameDto.getPlayer02()[3]);
        assertEquals(NUMBER_OF_STONES, gameDto.getPlayer02()[4]);
        assertEquals(NUMBER_OF_STONES, gameDto.getPlayer02()[5]);

        assertEquals(0, gameDto.getWinner());
        assertEquals(1, gameDto.getPlayerToMoveNext());
    }
}