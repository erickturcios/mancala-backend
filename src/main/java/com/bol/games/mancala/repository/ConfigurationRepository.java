package com.bol.games.mancala.repository;

import com.bol.games.mancala.jpa.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ConfigurationRepository extends JpaRepository<Configuration, Long> {

    @Query(name = "findByGameSession", value = "SELECT c FROM Configuration c WHERE c.gameSession = :gameSession")
    Configuration findByGameSession( @Param("gameSession") String gameSession);

}
