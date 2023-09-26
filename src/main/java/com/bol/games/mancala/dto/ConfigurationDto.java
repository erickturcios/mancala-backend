package com.bol.games.mancala.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class ConfigurationDto {
    private Long id;

    private String gameSession;

    private Integer numberOfStones;

    private Boolean stepBackAllowed;

    private Boolean autorotate;

    private String alias1;

    private String alias2;

    private Date createdOn;

    private Date updatedOn;
}
