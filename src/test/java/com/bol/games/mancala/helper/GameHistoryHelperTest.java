package com.bol.games.mancala.helper;

import com.bol.games.mancala.constant.UnitTest;
import com.bol.games.mancala.jpa.Game;
import com.bol.games.mancala.jpa.GameHistory;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@UnitTest
class GameHistoryHelperTest {

    private final static String SESSION = "4a525c64-859d-4c95-99dd-44e8140dc5a1";

    @Test
    @Order(1)
    void toGameHistory() {
        var date = new Date();

        var game = new Game(99L, SESSION, 0,
                1, 2, 3,
                4, 5, 6,
                7, 8, 9,
                10, 11, 12,
                13, 0, 3,
                date, null);

        var  gameHistory = GameHistoryHelper.toGameHistory(game);

        assertEquals(game.getGameSession(),gameHistory.getGameSession());
        assertEquals(game.getPlayer01_index01(),gameHistory.getPlayer01_index01());
        assertEquals(game.getPlayer01_index02(),gameHistory.getPlayer01_index02());
        assertEquals(game.getPlayer01_index03(),gameHistory.getPlayer01_index03());
        assertEquals(game.getPlayer01_index04(),gameHistory.getPlayer01_index04());
        assertEquals(game.getPlayer01_index05(),gameHistory.getPlayer01_index05());
        assertEquals(game.getPlayer01_index06(),gameHistory.getPlayer01_index06());
        assertEquals(game.getPlayer02_index01(),gameHistory.getPlayer02_index01());
        assertEquals(game.getPlayer02_index02(),gameHistory.getPlayer02_index02());
        assertEquals(game.getPlayer02_index03(),gameHistory.getPlayer02_index03());
        assertEquals(game.getPlayer02_index04(),gameHistory.getPlayer02_index04());
        assertEquals(game.getPlayer02_index05(),gameHistory.getPlayer02_index05());
        assertEquals(game.getPlayer02_index06(),gameHistory.getPlayer02_index06());
        assertEquals(game.getPlayer01_total(),gameHistory.getPlayer01_total());
        assertEquals(game.getPlayer02_total(),gameHistory.getPlayer02_total());
        assertEquals(game.getPlayerToMoveNext(),gameHistory.getPlayerToMoveNext());
        assertEquals(game.getWinner(),gameHistory.getWinner());
    }

    @Test@Order(2)
    void toGame() {
        var date = new Date();

        var gameHistory = new GameHistory(99L, SESSION, 0,
                1, 2, 3,
                4, 5, 6,
                7, 8, 9,
                10, 11, 12,
                13, 0, 3,
                date);

        var  game = GameHistoryHelper.toGame(gameHistory);

        assertEquals(game.getGameSession(),gameHistory.getGameSession());
        assertEquals(game.getPlayer01_index01(),gameHistory.getPlayer01_index01());
        assertEquals(game.getPlayer01_index02(),gameHistory.getPlayer01_index02());
        assertEquals(game.getPlayer01_index03(),gameHistory.getPlayer01_index03());
        assertEquals(game.getPlayer01_index04(),gameHistory.getPlayer01_index04());
        assertEquals(game.getPlayer01_index05(),gameHistory.getPlayer01_index05());
        assertEquals(game.getPlayer01_index06(),gameHistory.getPlayer01_index06());
        assertEquals(game.getPlayer02_index01(),gameHistory.getPlayer02_index01());
        assertEquals(game.getPlayer02_index02(),gameHistory.getPlayer02_index02());
        assertEquals(game.getPlayer02_index03(),gameHistory.getPlayer02_index03());
        assertEquals(game.getPlayer02_index04(),gameHistory.getPlayer02_index04());
        assertEquals(game.getPlayer02_index05(),gameHistory.getPlayer02_index05());
        assertEquals(game.getPlayer02_index06(),gameHistory.getPlayer02_index06());
        assertEquals(game.getPlayer01_total(),gameHistory.getPlayer01_total());
        assertEquals(game.getPlayer02_total(),gameHistory.getPlayer02_total());
        assertEquals(game.getPlayerToMoveNext(),gameHistory.getPlayerToMoveNext());
        assertEquals(game.getWinner(),gameHistory.getWinner());
    }
}