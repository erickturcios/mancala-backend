package com.bol.games.mancala.controllers;

import com.bol.games.mancala.Exception.NotFoundException;
import com.bol.games.mancala.jpa.Configuration;
import com.bol.games.mancala.service.ConfigurationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/configuration")
@CrossOrigin
public class ConfigurationController {

    private final ConfigurationService configurationService;

    public ConfigurationController(
            ConfigurationService configurationService
    ){
        this.configurationService = configurationService;
    }

    @GetMapping()
    public List<Configuration> getList(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int size
    ){
        return this.configurationService.getList(pageNumber, size);
    }

    @GetMapping("/{gameSession}")
    public Configuration getOne(
            @PathVariable("gameSession") String gameSession
    ){

        var config = this.configurationService.getOne(gameSession);

        if(config == null){
            throw new NotFoundException("Configuration not found for session with Id: {gameSession}" );
        }

        return config;

    }

    @PostMapping("/{gameSession}")
    public Configuration getOrCreate(
            @PathVariable("gameSession") String gameSession
    ){
        return this.configurationService.getOrCreate(gameSession);
    }

    @PostMapping()
    public Configuration save(@RequestBody Configuration c){
        return this.configurationService.save(c);
    }

    @DeleteMapping()
    public void delete(
            @RequestHeader("sessionId") String gameSession
    ){
        this.configurationService.delete(gameSession);
    }
}
