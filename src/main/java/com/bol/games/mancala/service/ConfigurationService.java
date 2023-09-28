package com.bol.games.mancala.service;

import com.bol.games.mancala.jpa.Configuration;

import java.util.List;

public interface ConfigurationService {

    List<Configuration> getList(
            int pageNumber, int size
    );

    Configuration getOne(
            String gameSession
    );

    Configuration getOrCreate(String gameSession);

    Configuration save(Configuration c);

    void delete(
            String gameSession
    );
}
