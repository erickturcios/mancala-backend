package com.bol.games.mancala.helper;

import com.bol.games.mancala.dto.GameDto;
import com.bol.games.mancala.jpa.Game;

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
                entity.getCreatedOn(),
                entity.getUpdatedOn());
    }
}
