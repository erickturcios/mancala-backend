package com.bol.games.mancala.jpa;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "game_boards")

public class Game {

    /*All args constructor is added by hand, to help identify fields position*/
    public Game(
            Long id, String gameSession, int player01_index01,
            int player01_index02, int player01_index03, int player01_index04,
            int player01_index05, int player01_index06, int player01_total,
            int player02_index01, int player02_index02, int player02_index03,
            int player02_index04, int player02_index05, int player02_index06,
            int player02_total, int playerToMoveNext, int winner,
            Date createdOn, Date updatedOn) {
        this.id = id;
        this.gameSession = gameSession;
        this.player01_index01 = player01_index01;
        this.player01_index02 = player01_index02;
        this.player01_index03 = player01_index03;
        this.player01_index04 = player01_index04;
        this.player01_index05 = player01_index05;
        this.player01_index06 = player01_index06;
        this.player01_total = player01_total;
        this.player02_index01 = player02_index01;
        this.player02_index02 = player02_index02;
        this.player02_index03 = player02_index03;
        this.player02_index04 = player02_index04;
        this.player02_index05 = player02_index05;
        this.player02_index06 = player02_index06;
        this.player02_total = player02_total;
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
        this.playerToMoveNext = playerToMoveNext;
        this.winner = winner;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "game_session")
    private String gameSession;

    @Column(name = "p1_01")
    private int player01_index01;
    @Column(name = "p1_02")
    private int player01_index02;
    @Column(name = "p1_03")
    private int player01_index03;
    @Column(name = "p1_04")
    private int player01_index04;
    @Column(name = "p1_05")
    private int player01_index05;
    @Column(name = "p1_06")
    private int player01_index06;
    @Column(name = "p1_total")
    private int player01_total;

    @Column(name = "p2_01")
    private int player02_index01;
    @Column(name = "p2_02")
    private int player02_index02;
    @Column(name = "p2_03")
    private int player02_index03;
    @Column(name = "p2_04")
    private int player02_index04;
    @Column(name = "p2_05")
    private int player02_index05;
    @Column(name = "p2_06")
    private int player02_index06;
    @Column(name = "p2_total")
    private int player02_total;

    @Column(name = "player_to_move_next")
    private int playerToMoveNext;

    @Column(name = "winner")
    private int winner;

    @Column(name = "created_on")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;

    @Column(name = "updated_on")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedOn;

    @PrePersist
    void setCreationDate() {
        this.createdOn = new Date();
    }

    @PreUpdate
    void setUpdateDate() {
        this.updatedOn = new Date();
    }

}
