package com.bol.games.mancala.helper;

import com.bol.games.mancala.dto.GameDto;
import com.bol.games.mancala.jpa.Game;

import java.util.Arrays;

public class GameHelper {
    public static Game toEntity(GameDto dto){
        if(dto.getPlayer01() == null || dto.getPlayer01().length < 6){
            dto.setPlayer01(new int[6]);
        }
        if(dto.getPlayer02() == null || dto.getPlayer02().length < 6){
            dto.setPlayer02(new int[6]);
        }
        int[] p1 = dto.getPlayer01();
        int[] p2 = dto.getPlayer02();

        return new Game(
                dto.getId(), dto.getGameSession(),
                p1[0],p1[1],p1[2],p1[3],p1[4],p1[5], dto.getTotalPlayer1(),
                p2[0],p2[1],p2[2],p2[3],p2[4],p2[5], dto.getTotalPlayer2(),
                dto.getPlayerToMoveNext(),
                dto.getWinner(),
                dto.getCreatedOn(),
                dto.getUpdatedOn());
    }

    public static GameDto toDto(Game entity){
        int[] p1 = new int[]{
                entity.getPlayer01_index01(),
                entity.getPlayer01_index02(),
                entity.getPlayer01_index03(),
                entity.getPlayer01_index04(),
                entity.getPlayer01_index05(),
                entity.getPlayer01_index06(),
                    };

        int[] p2 = new int[]{
                entity.getPlayer02_index01(),
                entity.getPlayer02_index02(),
                entity.getPlayer02_index03(),
                entity.getPlayer02_index04(),
                entity.getPlayer02_index05(),
                entity.getPlayer02_index06(),
        };

        return new GameDto(
                entity.getId(), entity.getGameSession(),
                p1, entity.getPlayer01_total(),
                p2, entity.getPlayer02_total(),
                entity.getPlayerToMoveNext(),
                entity.getWinner(),
                entity.getCreatedOn(),
                entity.getUpdatedOn());
    }

    public static int[] convertGameToIntArray(Game game){

        return new int[]{
                game.getPlayer01_index01(),game.getPlayer01_index02(),
                game.getPlayer01_index03(),game.getPlayer01_index04(),
                game.getPlayer01_index05(),game.getPlayer01_index06(),
                game.getPlayer01_total(),
                game.getPlayer02_index01(),game.getPlayer02_index02(),
                game.getPlayer02_index03(),game.getPlayer02_index04(),
                game.getPlayer02_index05(),game.getPlayer02_index06(),
                game.getPlayer02_total()
        };
    }

    public static void convertIntArrayToGame(Game game, int[]board){
        game.setPlayer01_index01(board[0]);
        game.setPlayer01_index02(board[1]);
        game.setPlayer01_index03(board[2]);
        game.setPlayer01_index04(board[3]);
        game.setPlayer01_index05(board[4]);
        game.setPlayer01_index06(board[5]);
        game.setPlayer01_total(board[6]);
        game.setPlayer02_index01(board[7]);
        game.setPlayer02_index02(board[8]);
        game.setPlayer02_index03(board[9]);
        game.setPlayer02_index04(board[10]);
        game.setPlayer02_index05(board[11]);
        game.setPlayer02_index06(board[12]);
        game.setPlayer02_total(board[13]);
    }


    /**
     * Always when the last stone lands in an own empty pit,
     * the player captures his own stone and all stones in the opposite pit
     * (the other player’s pit) and puts them in his own pit
     * @param board array of current board values
     * @param idx current index being evaluated
     */
    public static void evaluateEmptyPit(int[] board, int playerId, int idx){
        //if value equal to 1, it means it was empty
        if(board[idx] != 1){
            return;
        }
        if(playerId == 1 && !(idx < 6)){
            return;
        }
        if(playerId == 2 && !(idx >= 7 && idx <13)){
            return;
        }

        int oppositeIndex = 12 - idx;
        int takenStones = board[oppositeIndex] + board[idx];
        board[idx]--;
        board[oppositeIndex] = 0;

        if(playerId ==1){
            board[6] += takenStones;
        }else{
            board[13] += takenStones;
        }
    }


    public static int evaluateGameOver(int[] board){
        var p1 = 0;
        var p2 = 0;
        var  finalBoard = new int[board.length];

        for(int i=0; i<board.length; i++){
            p1 += (i < 6) ? board[i]: 0;
            p2 += (i>=7 && i < 13) ? board[i] : 0;
            finalBoard[i] = (i == 6|| i == 13) ? board[i] : 0;
        }
        var gameOver = p1 == 0 || p2 == 0;
        if(p1 == 0){
            finalBoard[13] += p2;
            System.arraycopy(finalBoard, 0, board, 0, finalBoard.length);
        }else if(p2 == 0){
            finalBoard[6] += p1;
            System.arraycopy(finalBoard, 0, board, 0, finalBoard.length);
        }

        var winner = 0;

        if(gameOver){
            if(finalBoard[6] > finalBoard[13]){
                winner = 1;
            }else if(finalBoard[6] < finalBoard[13]){
                winner = 2;
            }else{
                winner = 3; //tie
            }
        }

        return winner;
    }

    private void replaceBoardValues(int[] source, int[] target){
        if(source == null || target == null){
            return;
        }
        if(source.length != target.length){
            return;
        }

    }


}
