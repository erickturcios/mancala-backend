package com.bol.games.mancala.repository;

import com.bol.games.mancala.constant.IntegrationTest;
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
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Sql({"/insert_game_boards_history.sql"})
@IntegrationTest
class GameHistoryRepositoryTest {

    @Autowired
    private GameHistoryRepository gameHistoryRepository;

    @Test
    @Order(1)

    void findByGameSession() {
        final String sessionIdTrue = "4a525c64-859d-4c95-99dd-44e8140dc5a1";
        final String sessionIdFalse = "any-string";
        var exists = !gameHistoryRepository.findByGameSession(sessionIdTrue).isEmpty();
        assertTrue(exists);

        exists = !gameHistoryRepository.findByGameSession(sessionIdFalse).isEmpty();
        assertFalse(exists);
    }

    @Test
    @Order(2)
    void findTop1ByGameSessionOrderByCreatedOnDesc() {
        final String sessionId = "4a525c64-859d-4c95-99dd-44e8140dc5a1";
        var top = gameHistoryRepository.findTop1ByGameSessionOrderByCreatedOnDesc(sessionId);
        assertEquals(47, top.getId());

        assertEquals(1, top.getPlayerToMoveNext());
        assertEquals(0, top.getWinner());
    }

    @Test
    @Order(3)
    void deleteByGameSession() {
        final String sessionId = "4a525c64-859d-4c95-99dd-44e8140dc5a1";
        gameHistoryRepository.deleteByGameSession(sessionId);
        var empty = gameHistoryRepository.findByGameSession(sessionId).isEmpty();
        assertTrue(empty);
    }
}