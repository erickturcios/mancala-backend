package com.bol.games.mancala.controllers;

import com.bol.games.mancala.Exception.NotFoundException;
import com.bol.games.mancala.jpa.Configuration;
import com.bol.games.mancala.jpa.GameSession;
import com.bol.games.mancala.repository.ConfigurationRepository;
import com.bol.games.mancala.repository.GameSessionRepository;
import com.bol.games.mancala.service.ConfigurationDefaultsService;

import jakarta.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/configuration")
@CrossOrigin
public class ConfigurationController {

    private final ConfigurationRepository configurationRepository;
    private final GameSessionRepository gameSessionRepository;

    private final ConfigurationDefaultsService configDefaultsService;

    public ConfigurationController(
            ConfigurationRepository configurationRepository,
            GameSessionRepository gameSessionRepository,
            ConfigurationDefaultsService configDefaultsService
    ){
        this.configurationRepository = configurationRepository;
        this.gameSessionRepository = gameSessionRepository;
        this.configDefaultsService = configDefaultsService;
    }

    @GetMapping()
    public List<Configuration> get(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int size
    ){
        Pageable page = PageRequest.of(pageNumber, size);
        Page<Configuration> c = this.configurationRepository.findAll(page);

        return c.getContent();
    }

    @GetMapping("/{gameSession}")
    public Configuration getOne(
            @PathVariable("gameSession") String gameSession
    ){
        boolean exists = this.gameSessionRepository.existsBySessionId(gameSession);
        if(!exists){
            throw new NotFoundException("Session ID does not exist");
        }
        Configuration c = this.configurationRepository.findByGameSession(gameSession);
        if(c == null){
            throw new NotFoundException("Configuration not found for session with Id: " + gameSession );
        }

        return c;
    }

    @PostMapping("/{gameSession}")
    public Configuration getOrCreateOne(
            @PathVariable("gameSession") String gameSession
    ){
        boolean exists = this.gameSessionRepository.existsBySessionId(gameSession);
        String sessionId;
        if(!exists){
            GameSession session = new GameSession();
            session.setSessionId(UUID.randomUUID().toString());
            session = this.gameSessionRepository.save(session);
            sessionId = session.getSessionId();
        }else{
            sessionId = gameSession;
        }

        Configuration c = this.configurationRepository.findByGameSession(sessionId);
        if(c == null){
            Configuration newObj = new Configuration();
            newObj.setGameSession(sessionId);
            //set default values, defined in application.properties
            newObj.setNumberOfStones(this.configDefaultsService.getNumberOfStones());
            newObj.setStepBackAllowed(this.configDefaultsService.getStepBackAllowed());
            newObj.setAutorotate(this.configDefaultsService.getAutorotate());
            newObj.setAlias1(this.configDefaultsService.getAlias1());
            newObj.setAlias2(this.configDefaultsService.getAlias2());
            c = this.configurationRepository.save(newObj);
        }

        return c;
    }

    @PostMapping()
    public Configuration save(@RequestBody Configuration c){
        Configuration obj = this.configurationRepository.findByGameSession(c.getGameSession());
        if(obj == null){
            obj = new Configuration();
            obj.setGameSession(c.getGameSession());
            obj.setNumberOfStones(c.getNumberOfStones());
            obj.setStepBackAllowed(c.getStepBackAllowed());
            obj.setAutorotate(c.getAutorotate());
            obj.setAlias1(c.getAlias1());
            obj.setAlias2(c.getAlias2());
        }else{
            obj.setGameSession(c.getGameSession());
            obj.setNumberOfStones(c.getNumberOfStones());
            obj.setStepBackAllowed(c.getStepBackAllowed());
            obj.setAutorotate(c.getAutorotate());
            obj.setAlias1(c.getAlias1());
            obj.setAlias2(c.getAlias2());
        }

        obj = this.configurationRepository.save(obj);

        return obj;
    }

    @DeleteMapping()
    @Transactional
    public void delete(
            @RequestHeader("sessionId") String gameSession
    ){
        Configuration obj = this.configurationRepository.findByGameSession(gameSession);
        if(obj != null){
            this.configurationRepository.deleteById(obj.getId());
        }
        this.gameSessionRepository.deleteBySessionId(gameSession);
    }
}
