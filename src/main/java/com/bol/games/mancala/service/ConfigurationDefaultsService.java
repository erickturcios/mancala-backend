package com.bol.games.mancala.service;

import com.bol.games.mancala.jpa.Configuration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Service
public class ConfigurationDefaultsService {
    @Value("${game.defaults.numberOfStones}")
    private Integer numberOfStones;

    @Value("${game.defaults.stepBackAllowed}")
    private Boolean stepBackAllowed;

    @Value("${game.defaults.autorotate}")
    private Boolean autorotate;

    @Value("${game.defaults.alias1}")
    private String alias1;

    @Value("${game.defaults.alias2}")
    private String alias2;

    public Configuration getNewDefaultedInstance(String sessionId){
        var newObj = new Configuration();
        newObj.setGameSession(sessionId);
        //set default values, defined in application.properties
        newObj.setNumberOfStones(this.getNumberOfStones());
        newObj.setStepBackAllowed(this.getStepBackAllowed());
        newObj.setAutorotate(this.getAutorotate());
        newObj.setAlias1(this.getAlias1());
        newObj.setAlias2(this.getAlias2());
        return newObj;
    }
}
