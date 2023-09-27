package com.bol.games.mancala.repository;

import com.bol.games.mancala.jpa.GameSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameSessionRepository extends JpaRepository<GameSession, Long> {
    boolean existsBySessionId(String sessionId);
    void deleteBySessionId(String sessionId);


}
