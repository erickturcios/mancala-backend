package com.bol.games.mancala.jpa;

import jakarta.persistence.*;


import java.util.Date;
import java.util.Objects;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "configurations")

public class Configuration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "game_session")
    private String gameSession;

    @Column(name = "number_of_stones")
    private Integer numberOfStones;

    @Column(name = "step_back_allowed")
    private Boolean stepBackAllowed;

    @Column(name = "auto_rotate")
    private Boolean autorotate;

    @Column(name = "alias1")
    private String alias1;

    @Column(name = "alias2")
    private String alias2;

    @Column(name = "created_on")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;

    @Column(name = "updated_on")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedOn;


    @PrePersist
    void setCreationDate(){
        this.createdOn = new Date();
    }

    @PreUpdate
    void setUpdateDate(){
        this.updatedOn = new Date();
    }
}
