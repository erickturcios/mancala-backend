package com.bol.games.mancala.repository;

import com.bol.games.mancala.constant.IntegrationTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql({"/insert_game_boards.sql"})
@IntegrationTest
class GameRepositoryTest {

    @Autowired
    GameRepository gameRepository;

    @Test
    @DisplayName("findByGameSession")
    @Order(1)
    void findByGameSession() {
        final String sessionIdTrue = "4a525c64-859d-4c95-99dd-44e8140dc5a1";
        final String sessionIdFalse = "any-string";
        var exists = gameRepository.findByGameSession(sessionIdTrue) != null;
        assertTrue(exists);

        var notExists = gameRepository.findByGameSession(sessionIdFalse) == null;
        assertTrue(notExists);
    }
}