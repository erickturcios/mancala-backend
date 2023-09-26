package com.bol.games.mancala.repository;

import com.bol.games.mancala.jpa.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GameRepository extends JpaRepository<Game, Long> {
    @Query(name = "findByGameSession", value = "SELECT c FROM Game c WHERE c.gameSession = :gameSession")
    Game findByGameSession( @Param("gameSession") String gameSession);

}
