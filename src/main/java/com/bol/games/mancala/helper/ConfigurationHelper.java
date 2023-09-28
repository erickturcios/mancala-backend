package com.bol.games.mancala.helper;

import com.bol.games.mancala.dto.ConfigurationDto;
import com.bol.games.mancala.jpa.Configuration;

public class ConfigurationHelper {
    public static Configuration toEntity(ConfigurationDto dto){
        if(dto == null){
            return null;
        }
        return new Configuration(
                dto.getId(), dto.getGameSession(),
                dto.getNumberOfStones(), dto.getStepBackAllowed(),
                dto.getAutorotate(), dto.getAlias1(),
                dto.getAlias2(), dto.getCreatedOn(),
                dto.getUpdatedOn());
    }

    public static ConfigurationDto toDto(Configuration entity){
        if(entity == null){
            return null;
        }
        return new ConfigurationDto(
                entity.getId(), entity.getGameSession(),
                entity.getNumberOfStones(), entity.getStepBackAllowed(),
                entity.getAutorotate(), entity.getAlias1(),
                entity.getAlias2(), entity.getCreatedOn(),
                entity.getUpdatedOn());
    }
}
