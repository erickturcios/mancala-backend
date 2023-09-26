package com.bol.games.mancala.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class GameDto {

    public GameDto(
            long id, String gameSession, int[] player01, int totalPlayer1,
            int[] player02, int totalPlayer2, Date createdOn, Date updatedOn) {
        this.id = id;
        this.gameSession = gameSession;
        this.player01 = player01;
        this.totalPlayer1 = totalPlayer1;
        this.player02 = player02;
        this.totalPlayer2 = totalPlayer2;
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
    }

    private ConfigurationDto configuration;
    private long id;
    private String gameSession;
    private int[] player01;
    private int totalPlayer1;
    private int[] player02;
    private int totalPlayer2;
    private Date createdOn;
    private Date updatedOn;

}
