package com.bol.games.mancala.repository;

import com.bol.games.mancala.jpa.GameSession;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql({"/insert_sessions.sql"})
class GameSessionRepositoryTest {

    @Autowired
    GameSessionRepository gameSessionRepository;

    @Test
    @DisplayName("existsBySessionId")
    @Order(1)
    void existsBySessionId() {
        final String sessionIdTrue = "78fd56a1-aa24-471d-a2f3-51ecaf8c59ae";
        final String sessionIdFalse = "any-string";
        var exists = gameSessionRepository.existsBySessionId(sessionIdTrue);
        assertTrue(exists);

        exists = gameSessionRepository.existsBySessionId(sessionIdFalse);
        assertFalse(exists);
    }

    @Test
    @DisplayName("createSessionId")
    @Order(2)
    void createSessionId() {
        final String sessionId = "ToBeGenerated";
        var game = new GameSession();
        game.setId(0L);
        game.setSessionId(sessionId);

        game = gameSessionRepository.save(game);

        var exists = gameSessionRepository.existsBySessionId(game.getSessionId());
        assertTrue(exists);

    }

    @Test
    @DisplayName("deleteBySessionId")
    @Order(3)
    void deleteBySessionId() {
        final String sessionId = "78fd56a1-aa24-471d-a2f3-51ecaf8c59ae";
        var size = gameSessionRepository.findAll().size();
        assertEquals(4, size);

        var exists = gameSessionRepository.existsBySessionId(sessionId);
        assertTrue(exists);

        //remove 1 session
        gameSessionRepository.deleteBySessionId(sessionId);

        //size is 1 less than original value
        size = gameSessionRepository.findAll().size();
        assertEquals(3, size);

    }
}