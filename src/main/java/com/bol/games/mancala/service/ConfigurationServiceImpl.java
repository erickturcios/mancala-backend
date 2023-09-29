package com.bol.games.mancala.service;

import com.bol.games.mancala.jpa.Configuration;
import com.bol.games.mancala.jpa.GameSession;
import com.bol.games.mancala.repository.ConfigurationRepository;
import com.bol.games.mancala.repository.GameSessionRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class ConfigurationServiceImpl implements ConfigurationService{

    private final GameSessionRepository gameSessionRepository;
    private final ConfigurationRepository configurationRepository;
    private final ConfigurationDefaultsService configDefaultsService;

    public ConfigurationServiceImpl(
            GameSessionRepository gameSessionRepository,
            ConfigurationRepository configurationRepository,
            ConfigurationDefaultsService configDefaultsService
    ){
        this.gameSessionRepository = gameSessionRepository;
        this.configurationRepository = configurationRepository;
        this.configDefaultsService = configDefaultsService;
    }

    @Override
    public List<Configuration> getList(
            int pageNumber, int size
    ){
        var page = PageRequest.of(pageNumber, size);
        var c = this.configurationRepository.findAll(page);

        return c.getContent();
    }

    @Override
    public Configuration getOne(
            String gameSession
    ){
        var exists = this.gameSessionRepository.existsBySessionId(gameSession);
        if(!exists){
            return null;
        }
        return this.configurationRepository.findByGameSession(gameSession);
    }

    @Override
    public Configuration getOrCreate(String gameSession){
        var exists = this.gameSessionRepository.existsBySessionId(gameSession);
        String sessionId;
        if(!exists){
            var session = new GameSession();
            session.setSessionId(UUID.randomUUID().toString());
            session = this.gameSessionRepository.save(session);
            sessionId = session.getSessionId();
        }else{
            sessionId = gameSession;
        }

        var c = this.configurationRepository.findByGameSession(sessionId);
        if(c == null){
            var newObj = this.configDefaultsService.getNewDefaultedInstance(sessionId);
            c = this.configurationRepository.save(newObj);
        }

        return c;
    }

    @Override
    public Configuration save(Configuration c){
        var obj = this.configurationRepository.findByGameSession(c.getGameSession());
        if(obj == null){
            obj = new Configuration();
        }
        obj.setGameSession(c.getGameSession());
        obj.setNumberOfStones(c.getNumberOfStones());
        obj.setStepBackAllowed(c.getStepBackAllowed());
        obj.setAutorotate(c.getAutorotate());
        obj.setAlias1(c.getAlias1());
        obj.setAlias2(c.getAlias2());

        obj = this.configurationRepository.save(obj);

        return obj;
    }

    @Override
    @Transactional
    public void delete(
            String gameSession
    ){
        var obj = this.configurationRepository.findByGameSession(gameSession);
        if(obj != null){
            this.configurationRepository.deleteById(obj.getId());
        }
        this.gameSessionRepository.deleteBySessionId(gameSession);
    }
}
