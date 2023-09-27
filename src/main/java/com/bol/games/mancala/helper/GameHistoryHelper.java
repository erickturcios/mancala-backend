package com.bol.games.mancala.helper;

import com.bol.games.mancala.jpa.Game;
import com.bol.games.mancala.jpa.GameHistory;

import java.util.Date;

public class GameHistoryHelper {

    public static GameHistory toGameHistory(Game game){
        return new GameHistory(
                0L, game.getGameSession(), game.getPlayer01_index01(),
                game.getPlayer01_index02(), game.getPlayer01_index03(), game.getPlayer01_index04(),
                game.getPlayer01_index05(), game.getPlayer01_index06(), game.getPlayer01_total(),
                game.getPlayer02_index01(), game.getPlayer02_index02(), game.getPlayer02_index03(),
                game.getPlayer02_index04(), game.getPlayer02_index05(), game.getPlayer02_index06(),
                game.getPlayer02_total(), game.getPlayerToMoveNext(), game.getWinner(),
                game.getCreatedOn());
    }

    public static Game toGame(GameHistory gameHistory){
        return new Game(
                0L, gameHistory.getGameSession(), gameHistory.getPlayer01_index01(),
                gameHistory.getPlayer01_index02(), gameHistory.getPlayer01_index03(), gameHistory.getPlayer01_index04(),
                gameHistory.getPlayer01_index05(), gameHistory.getPlayer01_index06(), gameHistory.getPlayer01_total(),
                gameHistory.getPlayer02_index01(), gameHistory.getPlayer02_index02(), gameHistory.getPlayer02_index03(),
                gameHistory.getPlayer02_index04(), gameHistory.getPlayer02_index05(), gameHistory.getPlayer02_index06(),
                gameHistory.getPlayer02_total(), gameHistory.getPlayerToMoveNext(), gameHistory.getWinner(),
                gameHistory.getCreatedOn(), new Date());
    }
}
