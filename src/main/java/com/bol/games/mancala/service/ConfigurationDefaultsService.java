package com.bol.games.mancala.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ConfigurationDefaultsService {
    @Value("${mancala.defaults.numberOfStones}")
    private Integer numberOfStones;

    @Value("${mancala.defaults.stepBackAllowed}")
    private Boolean stepBackAllowed;

    @Value("${mancala.defaults.autorotate}")
    private Boolean autorotate;

    @Value("${mancala.defaults.alias1}")
    private String alias1;

    @Value("${mancala.defaults.alias2}")
    private String alias2;

    public Integer getNumberOfStones() {
        return numberOfStones;
    }

    public void setNumberOfStones(Integer numberOfStones) {
        this.numberOfStones = numberOfStones;
    }

    public Boolean getStepBackAllowed() {
        return stepBackAllowed;
    }

    public void setStepBackAllowed(Boolean stepBackAllowed) {
        this.stepBackAllowed = stepBackAllowed;
    }

    public Boolean getAutorotate() {
        return autorotate;
    }

    public void setAutorotate(Boolean autorotate) {
        this.autorotate = autorotate;
    }

    public String getAlias1() {
        return alias1;
    }

    public void setAlias1(String alias1) {
        this.alias1 = alias1;
    }

    public String getAlias2() {
        return alias2;
    }

    public void setAlias2(String alias2) {
        this.alias2 = alias2;
    }
}
