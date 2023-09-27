package com.bol.games.mancala.repository;

import com.bol.games.mancala.jpa.GameHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GameHistoryRepository extends JpaRepository<GameHistory, Long> {
    @Query(name = "findByGameSession", value = "SELECT c FROM GameHistory c WHERE c.gameSession = :gameSession ORDER BY c.createdOn DESC")
    List<GameHistory> findByGameSession(@Param("gameSession") String gameSession);

    void deleteByGameSession(String gameSession);

}
