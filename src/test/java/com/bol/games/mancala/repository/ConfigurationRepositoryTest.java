package com.bol.games.mancala.repository;


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
@Sql({"/insert_configurations.sql"})
class ConfigurationRepositoryTest {

    @Autowired
    private ConfigurationRepository configurationRepository;

    @Test
    void findByGameSession() {
        final String sessionIdTrue = "78fd56a1-aa24-471d-a2f3-51ecaf8c59ae";
        final String sessionIdFalse = "any-string";
        var exists = configurationRepository.findByGameSession(sessionIdTrue) != null;
        assertTrue(exists);

        exists = configurationRepository.findByGameSession(sessionIdFalse) != null;
        assertFalse(exists);
    }
}