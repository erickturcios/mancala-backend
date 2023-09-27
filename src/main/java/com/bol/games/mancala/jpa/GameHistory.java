package com.bol.games.mancala.jpa;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "game_boards_history")

public class GameHistory {

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

    @PrePersist
    void setCreationDate(){
        this.createdOn = new Date();
    }

}
