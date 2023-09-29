package com.bol.games.mancala.helper;

import com.bol.games.mancala.constant.UnitTest;
import com.bol.games.mancala.dto.GameDto;
import com.bol.games.mancala.jpa.Game;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@UnitTest
class GameHelperTest {

    private final static String SESSION = "4a525c64-859d-4c95-99dd-44e8140dc5a1";

    @Test
    @Order(1)
    void toEntity() {
        var date = new Date();
        int[] p1 = new int[]{0,1,2,3,4,5};
        int[] p2 = new int[]{6,7,8,9,10,11};

        var gameDto = new GameDto(99L, SESSION, p1, 12,
                p2, 13,
                3, 1,
                date, date);

        var  game = GameHelper.toEntity(gameDto);

        assertEquals(gameDto.getGameSession(),game.getGameSession());
        assertEquals(gameDto.getPlayer01()[0],game.getPlayer01_index01());
        assertEquals(gameDto.getPlayer01()[1],game.getPlayer01_index02());
        assertEquals(gameDto.getPlayer01()[2],game.getPlayer01_index03());
        assertEquals(gameDto.getPlayer01()[3],game.getPlayer01_index04());
        assertEquals(gameDto.getPlayer01()[4],game.getPlayer01_index05());
        assertEquals(gameDto.getPlayer01()[5],game.getPlayer01_index06());
        assertEquals(gameDto.getPlayer02()[0],game.getPlayer02_index01());
        assertEquals(gameDto.getPlayer02()[1],game.getPlayer02_index02());
        assertEquals(gameDto.getPlayer02()[2],game.getPlayer02_index03());
        assertEquals(gameDto.getPlayer02()[3],game.getPlayer02_index04());
        assertEquals(gameDto.getPlayer02()[4],game.getPlayer02_index05());
        assertEquals(gameDto.getPlayer02()[5],game.getPlayer02_index06());
        assertEquals(gameDto.getTotalPlayer1(),game.getPlayer01_total());
        assertEquals(gameDto.getTotalPlayer2(),game.getPlayer02_total());
        assertEquals(gameDto.getPlayerToMoveNext(),game.getPlayerToMoveNext());
        assertEquals(gameDto.getWinner(),game.getWinner());
    }

    @Test
    @Order(2)
    void toDto() {

        var date = new Date();

        var game = new Game(99L, SESSION, 0,
                1, 2, 3,
                4, 5, 6,
                7, 8, 9,
                10, 11, 12,
                13, 0, 3,
                date, date);

        var  gameDto = GameHelper.toDto(game);

        assertEquals(gameDto.getGameSession(),game.getGameSession());
        assertEquals(gameDto.getPlayer01()[0],game.getPlayer01_index01());
        assertEquals(gameDto.getPlayer01()[1],game.getPlayer01_index02());
        assertEquals(gameDto.getPlayer01()[2],game.getPlayer01_index03());
        assertEquals(gameDto.getPlayer01()[3],game.getPlayer01_index04());
        assertEquals(gameDto.getPlayer01()[4],game.getPlayer01_index05());
        assertEquals(gameDto.getPlayer01()[5],game.getPlayer01_index06());
        assertEquals(gameDto.getPlayer02()[0],game.getPlayer02_index01());
        assertEquals(gameDto.getPlayer02()[1],game.getPlayer02_index02());
        assertEquals(gameDto.getPlayer02()[2],game.getPlayer02_index03());
        assertEquals(gameDto.getPlayer02()[3],game.getPlayer02_index04());
        assertEquals(gameDto.getPlayer02()[4],game.getPlayer02_index05());
        assertEquals(gameDto.getPlayer02()[5],game.getPlayer02_index06());
        assertEquals(gameDto.getTotalPlayer1(),game.getPlayer01_total());
        assertEquals(gameDto.getTotalPlayer2(),game.getPlayer02_total());
        assertEquals(gameDto.getPlayerToMoveNext(),game.getPlayerToMoveNext());
        assertEquals(gameDto.getWinner(),game.getWinner());
    }

    @Test
    @Order(3)
    void convertGameToIntArray() {

        var date = new Date();
        var expected = new int[]{
                0,1,2,3,4,5,6,7,8,9,10,11,12,13
        };

        var game = new Game(99L, SESSION, 0,
                1, 2, 3,
                4, 5, 6,
                7, 8, 9,
                10, 11, 12,
                13, 0, 3,
                date, date);
        var result = GameHelper.convertGameToIntArray(game);

        assertEquals(expected[0],result[0]);
        assertEquals(expected[1],result[1]);
        assertEquals(expected[2],result[2]);
        assertEquals(expected[3],result[3]);
        assertEquals(expected[4],result[4]);
        assertEquals(expected[5],result[5]);
        assertEquals(expected[6],result[6]);
        assertEquals(expected[7],result[7]);
        assertEquals(expected[8],result[8]);
        assertEquals(expected[9],result[9]);
        assertEquals(expected[10],result[10]);
        assertEquals(expected[11],result[11]);
        assertEquals(expected[12],result[12]);
        assertEquals(expected[13],result[13]);
    }

    @Test
    @Order(4)
    void convertIntArrayToGame() {
        var input = new int[]{
                0,1,2,3,4,5,6,7,8,9,10,11,12,13
        };
        var game = new Game();

        GameHelper.convertIntArrayToGame(game, input);

        assertEquals(input[0],game.getPlayer01_index01());
        assertEquals(input[1],game.getPlayer01_index02());
        assertEquals(input[2],game.getPlayer01_index03());
        assertEquals(input[3],game.getPlayer01_index04());
        assertEquals(input[4],game.getPlayer01_index05());
        assertEquals(input[5],game.getPlayer01_index06());
        assertEquals(input[6],game.getPlayer01_total());
        assertEquals(input[7],game.getPlayer02_index01());
        assertEquals(input[8],game.getPlayer02_index02());
        assertEquals(input[9],game.getPlayer02_index03());
        assertEquals(input[10],game.getPlayer02_index04());
        assertEquals(input[11],game.getPlayer02_index05());
        assertEquals(input[12],game.getPlayer02_index06());
        assertEquals(input[13],game.getPlayer02_total());

    }

    @Test
    @Order(5)
    void evaluateEmptyPit() {
        var board = new int[]{
                7,7,7,7,1,0,1, 8,8,8,1,8,8,1
        };
        var expected = new int[]{
                7,7,7,7,0,0,10, 8,0,8,1,8,8,1
        };

        GameHelper.evaluateEmptyPit(board, 1, 4);
        assertEquals(expected[0],board[0]);
        assertEquals(expected[1],board[1]);
        assertEquals(expected[2],board[2]);
        assertEquals(expected[3],board[3]);
        assertEquals(expected[4],board[4]);
        assertEquals(expected[5],board[5]);
        assertEquals(expected[6],board[6]);
        assertEquals(expected[7],board[7]);
        assertEquals(expected[8],board[8]);
        assertEquals(expected[9],board[9]);
        assertEquals(expected[10],board[10]);
        assertEquals(expected[11],board[11]);
        assertEquals(expected[12],board[12]);
        assertEquals(expected[13],board[13]);
    }

    @Test
    @Order(6)
    void evaluateGameNotOver() {
        int result;
        var gameNotOverBoard = new int[]{
                7,7,7,7,1,0,1, 8,8,8,1,8,8,1
        };

        result = GameHelper.evaluateGameOver(gameNotOverBoard);
        assertEquals(result,0);
    }


    @Test
    @Order(7)
    void evaluateGameOverWinsPlayer1() {
        int result;
        var gameNotOverBoard = new int[]{
                0,0,0,0,0,0,44, 0,0,0,0,0,1,27
        };

        result = GameHelper.evaluateGameOver(gameNotOverBoard);
        assertEquals(result,1);
    }

    @Test
    @Order(8)
    void evaluateGameOverWinsPlayer2() {
        int result;
        var gameNotOverBoard = new int[]{
                0,0,0,0,0,1,27, 0,0,0,0,0,0,44
        };

        result = GameHelper.evaluateGameOver(gameNotOverBoard);
        assertEquals(result,2);
    }


    @Test
    @Order(8)
    void evaluateGameOverTied() {
        int result;
        var gameNotOverBoard = new int[]{
                0,0,0,0,0,0,36, 0,0,0,0,0,1,35
        };

        result = GameHelper.evaluateGameOver(gameNotOverBoard);
        assertEquals(result,3);
    }
}